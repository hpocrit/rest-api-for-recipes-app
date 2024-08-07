package ru.kpfu.itis.gabdullina.MyRestApi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.gabdullina.MyRestApi.dto.JwtReqDto;
import ru.kpfu.itis.gabdullina.MyRestApi.token.JwtTokenManager;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenManager jwtTokenManager;

    public String getToken(JwtReqDto req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        return jwtTokenManager.generateToken(userDetails);
    }

}
