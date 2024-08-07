package ru.kpfu.itis.gabdullina.MyRestApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.BaseResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ExceptionResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.MessageResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.UserDto;
import ru.kpfu.itis.gabdullina.MyRestApi.service.AdminService;

import java.util.List;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.SECURITY_SWAGGER;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.Constants.USER;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.CHANGE_ROLE_MESSAGE;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.getSuccessResp;

@Tag(name = "Admin", description = "Admin's management API")
@SecurityRequirement(name = SECURITY_SWAGGER)
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "Set admin role for user",
            description = "Update user role by id. Response has information about successful changed role",
            tags = "patch"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = MessageResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @PatchMapping("/set/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BaseResp> setAdmin(@PathVariable("id") @Min(1) Long id) {
        adminService.setAdmin(id);
        return ResponseEntity.ok(getSuccessResp(CHANGE_ROLE_MESSAGE, USER));
    }

    @Operation(
            summary = "Show us all users",
            description = "Collect all users. Response has list of user with id, name and role",
            tags = "get"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "403", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
                    @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ExceptionResp.class), mediaType = MediaType.APPLICATION_JSON_VALUE)})
            }
    )
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

}
