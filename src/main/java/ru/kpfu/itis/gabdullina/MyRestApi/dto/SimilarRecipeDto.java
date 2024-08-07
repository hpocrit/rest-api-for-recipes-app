package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.IMAGE_PATTERN;

@Getter
@Setter
@Schema(description = "Entity of similar recipe")
public class SimilarRecipeDto {
    @NotBlank(message = "Enter title")
    @Schema(description = "Title", example = "Steak")
    private String title;
    @NotBlank(message = "Enter image")
    @Pattern(regexp = IMAGE_PATTERN, message = "Incorrect image")
    @Schema(description = "image", example = "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663671165_50-mykaleidoscope-ru-p-tenderloin-steik-yeda-vkontakte-55.jpg")
    private String image;
}
