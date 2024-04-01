package com.codev.BackendCodGroup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return (WebMvcConfigurer) new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Permitir acesso a todos os endpoints
                        .allowedOrigins("http://localhost:8085/")  // Origem permitida
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos permitidos
                        .allowCredentials(true)
                        .allowedHeaders("Content-Type", "Authorization", "Cache-Control", "DNT", 
                                        "Origin", "X-Requested-With", "Accept", "Access-Control-Request-Method", 
                                        "Access-Control-Request-Headers", "Last-Modified")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .maxAge(3600);
            }
        };
    }
}
