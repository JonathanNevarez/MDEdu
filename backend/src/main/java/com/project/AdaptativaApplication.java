package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Punto de entrada de la base técnica; los módulos educativos se incorporan por fases. */
@SpringBootApplication
public class AdaptativaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaptativaApplication.class, args);
    }
}
