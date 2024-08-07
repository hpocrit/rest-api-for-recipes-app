package ru.kpfu.itis.gabdullina.MyRestApi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ExceptionResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.MessageResp;
import ru.kpfu.itis.gabdullina.MyRestApi.exception.RecipeNotFoundException;

import java.text.SimpleDateFormat;
import java.util.List;

@UtilityClass
public class RespUtils {

    public static final String DATE_PATTERN = "dd-MM-yyyy";

    public static final String CHANGE_ROLE_MESSAGE = "The role for %s has been successfully changed";
    public static final String CREATION_MESSAGE = "The %s have been successfully created";
    public static final String UPDATE_MESSAGE = "The %s have been successfully updated";
    public static final String DELETE_MESSAGE = "The %s have been successfully deleted";
    public static final String BAD_CREDENTIALS_EXCEPTION = "Incorrect username and password";
    public static final String DATA_NOT_VERIFY_EXCEPTION = "Data did not pass verification";
    public static final String DATA_SOURCE_EXCEPTION = "Data source couldn't be obtained";
    public static final String DATA_VALIDATION_EXCEPTION = "Data does not correspond to the required";
    public static final String HTTP_MESSAGE_NOT_READABLE_EXCEPTION = "Data is incorrect and leads to error";
    public static final String ENTITY_NOT_FOUND_EXCEPTION = "Specify the data";
    public static final String JPA_OBJECT_RETRIEVAL_FAILURE_EXCEPTION = "Data violates the established requirements";//нарушают установленные правила
    public static final String ACCESS_DENIED_EXCEPTION = "You do not have access";
    public static final String MALFORMED_JWT_EXCEPTION = "Incorrect token";
    public static final String EXPIRED_JWT_EXCEPTION = "Lifecycle of token is completed";
    public static final String SIGNATURE_EXCEPTION = "Incorrect signature";
    public static final String USER_NOT_FOUND_EXCEPTION  = "User with this id not found";
    public static final String RECIPE_NOT_FOUND_EXCEPTION = "Recipe with this id not found";


    public static MessageResp getSuccessResp(String message, String className) {
        return new MessageResp(HttpStatus.OK, String.format(message, className.toLowerCase()), className);
    }

    public static ExceptionResp getExceptionResp(HttpStatus status, String message, Exception exception) {
        return new ExceptionResp(status, message, exception.getClass().getSimpleName());
    }

    public static List<String> getErrorsValidationResponse(MethodArgumentNotValidException exception) {
        return exception
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }

    public static ObjectMapper getMapperWithTimeModule() {
        ObjectMapper mapper = JsonMapper
                .builder()
                .addModule(new JavaTimeModule())
                .build();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        mapper.setDateFormat(simpleDateFormat);
        return mapper;
    }

}
