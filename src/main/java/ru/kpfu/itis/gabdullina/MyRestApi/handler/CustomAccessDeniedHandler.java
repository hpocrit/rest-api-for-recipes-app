package ru.kpfu.itis.gabdullina.MyRestApi.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.BaseResp;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.ExceptionResp;

import java.io.IOException;

import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.getMapperWithTimeModule;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final CustomExceptionHandler customExceptionHandler;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseEntity<BaseResp> responseEntity = customExceptionHandler.handleException(accessDeniedException);
        ExceptionResp exceptionResp = (ExceptionResp) responseEntity.getBody();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if(exceptionResp != null) {
            response.setStatus(exceptionResp.getStatusCode());
        }
        ObjectMapper mapper = getMapperWithTimeModule();
        response.getWriter().write(mapper.writeValueAsString(exceptionResp));
    }
}
