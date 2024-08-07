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
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.*;
import ru.kpfu.itis.gabdullina.MyRestApi.service.CommentService;
import ru.kpfu.itis.gabdullina.MyRestApi.service.RecipeService;

import java.util.List;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.RECIPE;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.CREATION_MESSAGE;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.getSuccessResp;

@Tag(name = "Comments", description = "Comment's management API")
@SecurityRequirement(name = SECURITY_SWAGGER)
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @Operation(
            summary = "Show us list of all comment by recipe id",
            description = "Collect all recipes with certain id. Response is list of comment with id, recipe id, value and rating ",
            tags = "get"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = CommentDto.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByRecipeId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(commentService.getAllCommentsByRecipeId(id));
    }

    @Operation(
            summary = "Create new comment",
            description = "Create new comment. Response is a message about successful creation of comment",
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
    public ResponseEntity<BaseResp> createComment(@RequestBody @Valid CommentDto comment) {
        commentService.save(comment);
        return ResponseEntity.ok(getSuccessResp(CREATION_MESSAGE, "Comment"));
    }
}
