package ru.kpfu.itis.gabdullina.MyRestApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.*;
import ru.kpfu.itis.gabdullina.MyRestApi.service.RecipeService;

import java.util.List;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.RECIPE;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.*;

@Tag(name = "Recipe", description = "Recipe's management API")
@SecurityRequirement(name = SECURITY_SWAGGER)
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @Operation(
            summary = "Show us list of all recipes",
            description = "Collect all recipes. Response is list of recipes with id, title and image url",
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
    @GetMapping()
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @Operation(
            summary = "Show us recipe by id",
            description = "Get recipe by id. Response is recipe with id, title and image url",
            tags = "get"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = RecipeDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable("id") @Min(1) Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }

    @Operation(
            summary = "Create new recipe",
            description = "Create new recipe. Response is a message about successful creation of recipe",
            tags = "post"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MessageResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "400", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorValidatorResp.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @PostMapping("/create")
    public ResponseEntity<BaseResp> createRecipe(@RequestBody @Valid RecipeDto recipe) {
        recipeService.save(recipe);
        return ResponseEntity.ok(getSuccessResp(CREATION_MESSAGE, RECIPE));
    }

    @Operation(
            summary = "Update recipe by id",
            description = "Update recipe by id. Response is a message about successful update of user",
            tags = "patch"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MessageResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "400", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorValidatorResp.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResp> updateRecipe(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid RecipeDto recipe) {
        recipeService.update(id, recipe);
        return ResponseEntity.ok(getSuccessResp(UPDATE_MESSAGE, RECIPE));
    }

    @Operation(
            summary = "Delete recipe by id",
            description = "Delete recipe by id. Response is a message about successful delete of user",
            tags = "delete"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MessageResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResp> deleteRecipe(@PathVariable("id") @Min(1) Long id) {
        recipeService.delete(id);
        return ResponseEntity.ok(getSuccessResp(DELETE_MESSAGE, RECIPE));
    }

}
