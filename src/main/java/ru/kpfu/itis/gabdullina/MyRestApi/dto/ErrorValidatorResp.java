package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ErrorValidatorResp extends BaseResp{

    @Schema(description = "List of validation error", example = "a lot of text, a lot of letters")
    private List<String> errors;
    @Schema(description = "Error validation message", example = "Some valid exception")
    private String message;

    public ErrorValidatorResp(HttpStatus statusCode, List<String> errors, String message) {
        super(statusCode.value());
        this.errors = errors;
        this.message = message;
    }
}
