package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.ROLE_USER;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Entity of user")
public class UserDto extends BaseDto{

    @Schema(description = "Username", example = "Aliya")
    private String username;
    @Schema(description = "Role", example = ROLE_USER)
    private String role;

}
