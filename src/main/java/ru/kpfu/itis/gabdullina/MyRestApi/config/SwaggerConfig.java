package ru.kpfu.itis.gabdullina.MyRestApi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;

@Component
public class SwaggerConfig {

    @Value("${hpocrit.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI setOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in developer environment");
        Contact contact = new Contact();
        contact.setEmail("aliyagabdullina18@gmail.com");
        contact.setName("Aliya");
        contact.setUrl("https://github.com/hpocrit");
        Info info = new Info()
                .title("Rest Recipe Api")
                .version("1.0")
                .contact(contact)
                .description("This API will help you with management database of recipes an users protected with JWt token");
        final String securitySchemeName = SECURITY_SWAGGER;
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(info)
                .servers(List.of(devServer));
    }

}
