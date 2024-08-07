package ru.kpfu.itis.gabdullina.MyRestApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ExceptionResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.RecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.SimilarRecipeDto;
import ru.kpfu.itis.gabdullina.MyRestApi.service.SimilarRecipesService;

import java.util.List;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;

@Tag(name = "Similar Recipes", description = "Similar Recipe's management API")
@SecurityRequirement(name = SECURITY_SWAGGER)
@RestController
@RequestMapping("/similar")
@RequiredArgsConstructor
public class SimilarRecipesController {

    private final SimilarRecipesService similarRecipesService;

    @Operation(
            summary = "Show us list of similar recipes",
            description = "Collect all similar recipes. Response is list of recipes with id, title and image url",
            tags = "get"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = RecipeDto.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @GetMapping("/{recipeName}")
    public ResponseEntity<List<SimilarRecipeDto>> getSimilarRecipes(@PathVariable("recipeName")String recipeName) {
        return ResponseEntity.ok(similarRecipesService.getSimilarRecipes(recipeName));
    }


}
