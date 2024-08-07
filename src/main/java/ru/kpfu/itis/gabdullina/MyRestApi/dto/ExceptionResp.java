package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionResp extends BaseResp{

    @Schema(description = "Error response message", example = "Some response exception")
    private String message;
    @Schema(description = "Error type", example = "Some exception type")
    private String type;

    public ExceptionResp(HttpStatus statusCode, String message, String type) {
        super(statusCode.value());
        this.message = message;
        this.type = type;
    }
}
