package com.project.shared.infrastructure.config;

import com.project.shared.security.ApiErrors;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/** Handle only missing frontend documents; preserve status and the existing API error envelope. */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnProperty(name = "app.spa.enabled", havingValue = "true")
public class SpaNotFoundAdvice {
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> missing(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        String accept = request.getHeader("Accept");
        boolean document = request.getMethod().equals("GET") && accept != null && accept.contains("text/html")
                && !path.contains(".") && !path.matches("^/(api|actuator|assets|blockly-media)(/.*)?$");
        if (document) {
            return ResponseEntity.status(404).contentType(MediaType.TEXT_HTML).cacheControl(CacheControl.noStore())
                    .body(new ClassPathResource("static/index.html"));
        }
        return ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body(ApiErrors.body(404));
    }
}
