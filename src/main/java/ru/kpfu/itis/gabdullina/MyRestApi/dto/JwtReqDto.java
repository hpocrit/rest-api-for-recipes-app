package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.ENTER_PASSWORD;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.ENTER_USERNAME;

@Getter
@Setter
@Schema(description = "Entity of user")
public class JwtReqDto {

    @NotBlank(message = ENTER_USERNAME)
    @Schema(description = "Username", example = "Aliya")
    private String username;
    @NotBlank(message = ENTER_PASSWORD)
    @Schema(description = "Password", example = "Strong password")
    private String password;

}
