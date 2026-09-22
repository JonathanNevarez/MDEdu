package com.project;

import com.fasterxml.jackson.databind.JsonNode;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "spring.config.import=",
        "server.address=127.0.0.1",
        "app.cors.allowed-origins=http://localhost:5173,http://127.0.0.1:5173"
})
class BackendBootstrapIT {

    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:17-bookworm");

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }

    @Autowired
    private TestRestTemplate http;

    @Autowired
    private HealthContributorRegistry healthContributors;

    @Autowired
    private Flyway flyway;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void healthIncludesDatabaseCheckWithoutDisclosingDetails() {
        var response = http.getForEntity("/actuator/health", JsonNode.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().path("status").asText()).isEqualTo("UP");
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(healthContributors.getContributor("db")).isNotNull();
    }

    @Test
    void bootstrapMigrationRunsAgainstPostgresqlAndCreatesNoEducationalTables() {
        assertThat(flyway.info().current().getVersion().getVersion()).isEqualTo("1");
        assertThat(flyway.info().pending()).isEmpty();
        assertThat(jdbc.queryForObject("SELECT success FROM flyway_schema_history WHERE version = '1'",
                Boolean.class)).isTrue();
        assertThat(jdbc.queryForList("""
                SELECT tablename FROM pg_catalog.pg_tables
                WHERE schemaname = 'public' ORDER BY tablename
                """, String.class)).containsExactly("flyway_schema_history");
    }

    @Test
    void diagnosticEndpointsAreNotExposed() {
        var response = http.getForEntity("/actuator/env", JsonNode.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().has("trace")).isFalse();
        assertThat(response.getBody().has("exception")).isFalse();
        assertThat(response.getBody().has("message")).isFalse();
    }

    @Test
    void frontendCanReadHealthThroughActuatorCors() {
        var headers = new HttpHeaders();
        headers.setOrigin("http://localhost:5173");

        var response = http.exchange("/actuator/health", HttpMethod.GET,
                new HttpEntity<Void>(headers), JsonNode.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getAccessControlAllowOrigin())
                .isEqualTo("http://localhost:5173");
    }
}
