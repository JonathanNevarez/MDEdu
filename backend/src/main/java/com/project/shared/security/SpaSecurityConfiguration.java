package com.project.shared.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;

/** Public shell only. Existing API authorization and CSRF chains stay in force. */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "app.spa.enabled", havingValue = "true")
public class SpaSecurityConfiguration {
    public static final String CSP = "default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; "
            + "img-src 'self' data:; font-src 'self'; media-src 'self'; connect-src 'self'; "
            + "object-src 'none'; base-uri 'self'; frame-ancestors 'none'; form-action 'self'";

    @Bean @Order(0) SecurityFilterChain spa(HttpSecurity http) throws Exception {
        http.securityMatcher(request -> {
            String path = request.getServletPath();
            if (path.isEmpty()) path = request.getRequestURI().substring(request.getContextPath().length());
            return !(path.equals("/api") || path.startsWith("/api/")
                    || path.equals("/actuator") || path.startsWith("/actuator/"));
        }).authorizeHttpRequests(a -> a.anyRequest().permitAll()).requestCache(c -> c.disable())
          .headers(h -> h.contentTypeOptions(c -> {}).frameOptions(f -> f.deny())
              .referrerPolicy(r -> r.policy(ReferrerPolicy.NO_REFERRER))
              .contentSecurityPolicy(c -> c.policyDirectives(CSP)));
        return http.build();
    }
}
