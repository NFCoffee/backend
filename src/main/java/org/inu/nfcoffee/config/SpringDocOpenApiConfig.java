package org.inu.nfcoffee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "default generated url")})
@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(info());
    }


    private Info info() {
        return new Info()
                .title("NFCOFFEE API Document")
                .version("v0.1")
                .description("NFCOFFEE의 API 명세서입니다.");
    }

}