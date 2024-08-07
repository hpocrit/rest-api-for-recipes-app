package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.gabdullina.MyRestApi.validator.PasswordMatching;
import ru.kpfu.itis.gabdullina.MyRestApi.validator.UserExist;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.*;

@Getter
@Setter
@PasswordMatching
@Valid
@Schema(description = "Entity of user")
public class UserRegistrationDto {

    @NotBlank(message = ENTER_USERNAME)
    @Pattern(regexp = USERNAME_PATTERN, message = INCORRECT_USERNAME)
    @UserExist
    @Schema(description = "Username", example = "Aliya")
    private String username;
    @NotBlank(message = ENTER_PASSWORD)
    @Pattern(regexp = PASSWORD_PATTERN, message = INCORRECT_PASSWORD)
    @Schema(description = "Password", example = "Strong password")
    private String password;
    @NotBlank(message = ENTER_PASSWORD)
    @Pattern(regexp = PASSWORD_PATTERN, message = INCORRECT_PASSWORD)
    @Schema(description = "Confirm password", example = "Confirm strong password")
    private String confirmPassword;
}
