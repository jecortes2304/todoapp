package com.cortestudios.todoapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.api-docs.version}") String appVersion) {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                .name("Authorization")
                                .scheme("ApiKey")
                                .in(SecurityScheme.In.HEADER)
                                .description("Header api key to authenticate")
                                .bearerFormat("bearerAuth")
                ))
                .info(apiInfo(appVersion))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
    private Info apiInfo(String appVersion) {

        Contact contact = new Contact();
        contact.setName("CorteStudios");
        contact.setUrl("https://cortestudios-portfolio.netlify.app/");
        contact.setEmail("cortesstudios94@gmail.com");
        return new Info()
                .description("This is a detailed API REST to handle tasks")
                .summary("API REST to handle tasks")
                .contact(contact)
                .title("Todo API")
                .version(appVersion)
                .license(new License().name("Apache 2.0").url("https://springdoc.org"));
    }
}
