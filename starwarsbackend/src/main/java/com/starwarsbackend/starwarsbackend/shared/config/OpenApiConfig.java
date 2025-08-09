package com.starwarsbackend.starwarsbackend.shared.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI springStarWarsOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Star Wars API")
            .description("Backend API for Star Wars Web Application")
            .version("v1.0")
            .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT")))
        .externalDocs(new ExternalDocumentation()
            .description("SWAPI Documentation")
            .url("https://swapi.py4e.com/"));
  }
}
