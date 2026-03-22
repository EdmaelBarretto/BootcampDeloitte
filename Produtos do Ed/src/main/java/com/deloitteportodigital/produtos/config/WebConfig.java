package com.deloitteportodigital.produtos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")          // todas as rotas
                .allowedOrigins("*")        // qualquer origem
                .allowedMethods(            // métodos permitidos explicitamente
                        "GET", "POST", "PUT", "DELETE", "OPTIONS"
                )
                .allowedHeaders("*")
                .maxAge(3600);              // cache do preflight por 1 hora
    }
}