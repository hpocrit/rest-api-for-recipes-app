package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public abstract class BaseResp {

    @Schema(description = "status", example = "200")
    private Integer statusCode;
    @Schema(description = "timestamp", example = "2024-12-01")
    private final LocalDate timestamp = LocalDate.now();

}
