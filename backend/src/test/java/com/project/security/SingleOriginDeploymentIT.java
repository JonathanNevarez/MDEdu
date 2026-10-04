package com.project.security;

import java.net.URI;
import java.net.http.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@Testcontainers @ActiveProfiles({"prod", "render"})
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties={"LLM_PROVIDER=DISABLED", "RENDER_EXTERNAL_URL=https://demo.example.invalid"})
@AutoConfigureMockMvc @TestPropertySource(locations="classpath:teacher-test.properties")
class SingleOriginDeploymentIT {
    @Container static final PostgreSQLContainer<?> DB = new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void db(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url", DB::getJdbcUrl);
        r.add("spring.datasource.username", DB::getUsername);
        r.add("spring.datasource.password", DB::getPassword);
    }
    @Autowired MockMvc mvc;
    @LocalServerPort int port;

    @Test void onlyExplicitFrontendRoutesForward() throws Exception {
        for (String path : List.of("/", "/ingresar", "/aprender", "/progreso", "/laboratorio",
                "/docente", "/docente/login", "/aprender/COND-03", "/aventura/SEQ-01")) {
            mvc.perform(get(path)).andExpect(status().isOk()).andExpect(forwardedUrl("/index.html"))
                .andExpect(header().string("Content-Security-Policy", com.project.shared.security.SpaSecurityConfiguration.CSP));
        }
    }
    @Test void missingAssetsAndApiNeverReceiveSpaFallback() throws Exception {
        for (String path : List.of("/assets/missing.js", "/blockly-media/missing.svg", "/unknown", "/aprender/missing.js"))
            mvc.perform(get(path)).andExpect(status().isNotFound()).andExpect(result -> assertNull(result.getResponse().getForwardedUrl()));
        mvc.perform(get("/api/does-not-exist").with(user("teacher").roles("TEACHER")))
            .andExpect(status().isNotFound()).andExpect(result -> assertNull(result.getResponse().getForwardedUrl()));
        mvc.perform(get("/actuator/missing")).andExpect(status().isNotFound());
    }
    @Test void shellDoesNotGrantApiAccessOrBypassCsrf() throws Exception {
        mvc.perform(get("/api/meta/students")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/meta/students").with(user("student").roles("STUDENT"))).andExpect(status().isForbidden());
        mvc.perform(post("/api/teacher/login")).andExpect(status().isForbidden());
        mvc.perform(post("/api/auth/student/login").contentType("application/json").content("{}"))
            .andExpect(status().isForbidden());
        mvc.perform(get("/api/game/levels")).andExpect(status().isOk())
            .andExpect(header().string("Content-Security-Policy", "default-src 'none'; frame-ancestors 'none'; base-uri 'none'"));
    }
    @Test void forwardedHttpsRetainsCookieFlagsAndRejectsForeignOrigin() throws Exception {
        var request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:"+port+"/api/teacher/csrf"))
            .header("X-Forwarded-Proto", "https").header("X-Forwarded-Host", "demo.example.invalid")
            .header("X-Forwarded-Port", "443").header("Origin", "https://demo.example.invalid").GET().build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
        String cookie = response.headers().firstValue("set-cookie").orElseThrow();
        for (String flag : List.of("Secure", "HttpOnly", "SameSite=Strict")) assertTrue(cookie.contains(flag));
        mvc.perform(options("/api/teacher/csrf").header("Origin", "https://other.invalid")
                .header("Access-Control-Request-Method", "GET")).andExpect(status().isForbidden());
    }
    @Test void realStaticResponseUsesUiCspAndDoesNotCacheIndex() throws Exception {
        var request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:"+port+"/aprender")).GET().build();
        var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());assertTrue(response.body().contains("test-spa-shell"));
        assertEquals("nosniff", response.headers().firstValue("x-content-type-options").orElseThrow());
        assertEquals("DENY", response.headers().firstValue("x-frame-options").orElseThrow());
        assertTrue(response.headers().firstValue("cache-control").orElseThrow().contains("no-store"));
        assertTrue(response.headers().firstValue("content-security-policy").orElseThrow().contains("script-src 'self'"));
    }
    @Test void readinessAndLivenessRemainPublicAndMinimal() throws Exception {
        for (String path : List.of("/actuator/health/readiness", "/actuator/health/liveness"))
            mvc.perform(get(path)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.details").doesNotExist()).andExpect(jsonPath("$.components").doesNotExist());
    }
    @Test void browserUnknownDocumentKeeps404ButUsesAccessibleSpa() throws Exception {
        var client=HttpClient.newHttpClient();
        for(String accept:List.of("text/html", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")) {
            var response=client.send(HttpRequest.newBuilder(URI.create("http://127.0.0.1:"+port+"/no-existe"))
                    .header("Accept",accept).GET().build(),HttpResponse.BodyHandlers.ofString());
            assertEquals(404,response.statusCode());assertTrue(response.body().contains("test-spa-shell"));
            assertTrue(response.headers().firstValue("content-type").orElseThrow().startsWith("text/html"));
        }
        for(String path:List.of("/api/missing","/actuator/missing","/assets/missing.js","/blockly-media/missing.svg")){
            var response=client.send(HttpRequest.newBuilder(URI.create("http://127.0.0.1:"+port+path))
                    .header("Accept","text/html").GET().build(),HttpResponse.BodyHandlers.ofString());
            assertFalse(response.body().contains("test-spa-shell"));
        }
    }
}
