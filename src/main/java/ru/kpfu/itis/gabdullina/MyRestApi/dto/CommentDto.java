package ru.kpfu.itis.gabdullina.MyRestApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Entity of comment")
public class CommentDto extends BaseDto {
    @Min(0)
    @Schema(description = "recipe id", example = "2")
    private Long recipeId;
    @NotBlank(message = "Enter comment")
    @Schema(description = "Comment", example = "Cool!")
    private String value;
    @Max(6)
    @Schema(description = "rating", example = "4.5")
    private Float rating;
}
