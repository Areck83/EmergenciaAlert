package com.hiloxDevelop.EmergenciaAlert;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("https://emergenciaalert-production.up.railway.app","*", "emergenciaalert-production.up.railway.app")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowCredentials(false)
            .allowedHeaders("*");
    }
}
