package com.example.splitwiseapp.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI splitwiseAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Splitwise API")
                        .description("Microservices for managing users and transactions")
                        .version("1.0"));
    }
}
