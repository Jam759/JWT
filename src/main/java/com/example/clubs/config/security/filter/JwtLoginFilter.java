package com.example.clubs.config.security.filter;


import com.example.clubs.config.security.dto.LoginRequest;
import com.example.clubs.config.security.utill.JwtUtill;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtill jwtUtill;

    public JwtLoginFilter(AuthenticationManager authenticationManager, JwtUtill jwtUtill){
        this.authenticationManager = authenticationManager;
        this.jwtUtill = jwtUtill;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        try{

            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()
            );
            return authenticationManager.authenticate(authToken);
        }catch (IOException e){
            throw new RuntimeException("Invalid login request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException{
        String token = jwtUtill.generatedToken(authResult.getName());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(tokenMap));
    }


}
