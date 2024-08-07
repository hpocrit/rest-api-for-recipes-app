package ru.kpfu.itis.gabdullina.MyRestApi.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.BaseResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ErrorValidatorResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ExceptionResp;
import ru.kpfu.itis.gabdullina.MyRestApi.exception.RecipeNotFoundException;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.*;

@Component
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<BaseResp> handleException(BadCredentialsException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.UNAUTHORIZED,
                BAD_CREDENTIALS_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResp> handleException(MethodArgumentNotValidException exception) {
        ErrorValidatorResp resp = new ErrorValidatorResp(
                HttpStatus.BAD_REQUEST,
                getErrorsValidationResponse(exception),
                DATA_NOT_VERIFY_EXCEPTION
        );
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataSourceLookupFailureException.class)
    public ResponseEntity<BaseResp> handleException(DataSourceLookupFailureException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.INTERNAL_SERVER_ERROR,
                DATA_SOURCE_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BaseResp> handleException(DataIntegrityViolationException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.INTERNAL_SERVER_ERROR,
                DATA_VALIDATION_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResp> handleException(HttpMessageNotReadableException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.INTERNAL_SERVER_ERROR,
                HTTP_MESSAGE_NOT_READABLE_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BaseResp> handleException(EntityNotFoundException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.NOT_FOUND,
                ENTITY_NOT_FOUND_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<BaseResp> handleException(JpaObjectRetrievalFailureException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.INTERNAL_SERVER_ERROR,
                JPA_OBJECT_RETRIEVAL_FAILURE_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<BaseResp> handleException(RecipeNotFoundException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.NOT_FOUND,
                RECIPE_NOT_FOUND_EXCEPTION,
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResp> handleException(AccessDeniedException exception) {
        ExceptionResp resp = getExceptionResp(
                HttpStatus.FORBIDDEN,
                exception.getMessage(),
                exception
        );
        return new ResponseEntity<>(resp, HttpStatus.FORBIDDEN);
    }
}
