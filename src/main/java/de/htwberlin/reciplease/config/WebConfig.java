package de.htwberlin.reciplease.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Erlaubt alle HTTP-Methoden und -Header von allen aufgelisteten Quellen
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://reciplease1-fe-e3c474f21ef1.herokuapp.com"
                );
    }
}
