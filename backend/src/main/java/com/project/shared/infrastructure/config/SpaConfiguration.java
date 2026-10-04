package com.project.shared.infrastructure.config;

import java.time.Duration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;

/** Explicit frontend routes only: unknown API paths and missing assets remain errors. */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "app.spa.enabled", havingValue = "true")
public class SpaConfiguration implements WebMvcConfigurer {
    @Override public void addViewControllers(ViewControllerRegistry registry) {
        for (String path : new String[]{"/", "/ingresar", "/aprender", "/progreso", "/laboratorio",
                "/docente/login", "/docente", "/aventura", "/aprender/{levelId:[A-Z]+-[0-9]+}",
                "/aventura/{levelId:[A-Z]+-[0-9]+}"}) {
            registry.addViewController(path).setViewName("forward:/index.html");
        }
    }
    @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)).cachePublic().immutable());
        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.noStore());
    }
}
