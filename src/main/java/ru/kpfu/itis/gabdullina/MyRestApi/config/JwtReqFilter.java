package ru.kpfu.itis.gabdullina.MyRestApi.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kpfu.itis.gabdullina.MyRestApi.handler.CustomAccessDeniedHandler;
import ru.kpfu.itis.gabdullina.MyRestApi.token.JwtTokenManager;
import ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static ru.kpfu.itis.gabdullina.MyRestApi.utils.RespUtils.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtReqFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);

        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String username = jwtTokenManager.getUsername(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    jwtTokenManager
                                            .getRoles(token)
                                            .stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .toList()
                            );
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (MalformedJwtException exception) {
                createExceptionResponse(request, response, MALFORMED_JWT_EXCEPTION, exception);
                return;
            } catch (ExpiredJwtException exception) {
                createExceptionResponse(request, response, EXPIRED_JWT_EXCEPTION, exception);
                return;
            } catch (SignatureException exception) {
                createExceptionResponse(request, response, SIGNATURE_EXCEPTION, exception);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void createExceptionResponse(HttpServletRequest request, HttpServletResponse response, String message, Exception exception) throws IOException, ServletException {
        log.debug(exception.getMessage());
        customAccessDeniedHandler.handle(request, response, new AccessDeniedException(message));
    }
}
