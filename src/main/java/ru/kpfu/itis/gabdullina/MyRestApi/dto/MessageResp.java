package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MessageResp extends BaseResp {

    @Schema(description = "Message with good news", example = "Something is successfully happened")
    private String message;
    @JsonIgnore
    private Object object;

    public MessageResp(HttpStatus statusCode, String message, Object object) {
        super(statusCode.value());
        this.message = message;
        this.object = object;
    }
}
