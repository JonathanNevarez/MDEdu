package com.project.shared.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(properties = {
        "spring.config.import=",
        "app.cors.allowed-origins=http://localhost:5173,http://127.0.0.1:5173"
})
@Import({CorsConfiguration.class, CorsConfigurationTest.ProbeController.class})
class CorsConfigurationTest {

    @Autowired
    private MockMvc mvc;

    @ParameterizedTest
    @ValueSource(strings = {"http://localhost:5173", "http://127.0.0.1:5173"})
    void acceptsConfiguredFrontendOrigins(String origin) throws Exception {
        mvc.perform(get("/api/test-probe").header(HttpHeaders.ORIGIN, origin))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin))
                .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS));
    }

    @Test
    void rejectsUnlistedOrigin() throws Exception {
        mvc.perform(options("/api/test-probe")
                        .header(HttpHeaders.ORIGIN, "https://unlisted.example")
                        .header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "GET"))
                .andExpect(status().isForbidden())
                .andExpect(header().doesNotExist(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));
    }

    /** Solo existe en pruebas; no añade rutas de negocio a la aplicación. */
    @RestController
    static class ProbeController {

        @GetMapping("/api/test-probe")
        String probe() {
            return "ok";
        }
    }
}
