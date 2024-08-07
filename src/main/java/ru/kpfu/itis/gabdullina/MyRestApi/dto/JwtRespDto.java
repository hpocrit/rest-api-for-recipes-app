package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRespDto {

    @Schema(description = "Jwt Token", example = "a lot of letters.a lot of letters.a lot of letters")
    private String token;
    @Schema(description = "Your id", example = "1")
    private Long id;

}
