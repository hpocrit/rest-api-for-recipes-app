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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.*;
import ru.kpfu.itis.gabdullina.MyRestApi.service.AuthService;
import ru.kpfu.itis.gabdullina.MyRestApi.service.UserService;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.*;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.*;

@Tag(name = "Auth", description = "User's management API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @Operation(
            summary = "Create user token",
            description = "Get token by uor credentials. Response is jwt token",
            tags = "post"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = JwtReqDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "400", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = ErrorValidatorResp.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "401", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @PostMapping
    public ResponseEntity<JwtRespDto> createAuthToken(@RequestBody @Valid JwtReqDto req) throws BadCredentialsException {
        String token = authService.getToken(req);
        return ResponseEntity.ok(new JwtRespDto(token, userService.getIdByUsername(req.getUsername())));
    }

    @Operation(
            summary = "Create new user",
            description = "Create new user. Response is a message about successful creation of user",
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
    @PostMapping("/registration")
    public ResponseEntity<BaseResp> createNewUser(@RequestBody @Valid UserRegistrationDto user) {
        userService.saveUser(user);
        return ResponseEntity.ok(getSuccessResp(CREATION_MESSAGE, USER));
    }

    @Operation(
            summary = "Update user by id",
            description = "Update user by id. Response is a message about successful update of user",
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
    public ResponseEntity<BaseResp> updateRecipe(@PathVariable("id") @Min(1) Long id, @RequestBody @Valid JwtReqDto dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.ok(getSuccessResp(UPDATE_MESSAGE, RECIPE));
    }

    @Operation(
            summary = "Get username by token",
            description = "Get username by token. Response is username",
            tags = "get"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UsernameDto.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsernameDto> getUsernameById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new UsernameDto(userService.getUsernameById(id)));
    }
}
