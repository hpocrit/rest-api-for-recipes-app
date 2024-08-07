package ru.kpfu.itis.gabdullina.MyRestApi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;

@SecurityRequirement(name = SECURITY_SWAGGER)
@RestController
@RequestMapping("/main")
public class MainController { //для тестов

    @GetMapping("/unsecurity")
    public String getUnsecurityPage() {
        return "unsecurity page";
    }

    @GetMapping("/security")
    public String getSecurityPage() {
        return "security page";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin page";
    }

}
