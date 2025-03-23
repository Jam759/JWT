package com.example.clubs.config.security.filter;

import com.example.clubs.config.security.entity.User;
import com.example.clubs.config.security.utill.CookieUtil;
import com.example.clubs.config.security.utill.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private  final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtUtil jwtUtil, UserDetailsService userDetailsService){
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, SecurityException, ServletException {

        String token = jwtUtil.resolveToken(request);


        if (token != null) {

            if (jwtUtil.validateAccessToken(token)) { //엑세스 토큰 유효성 검사

                String username = jwtUtil.getUsernameFromAccessToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }else{

                CookieUtil.getCookieValue(request, "refreshToken")
                        .filter(jwtUtil::validateRefreshToken)
                        .map(jwtUtil::getUsernameFromRefreshToken)
                        .map(username -> {
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            Long userId = ((User) userDetails).getId();

                            String newAccessToken = jwtUtil.generateAccessToken(username,userId);
                            response.setHeader("Authorization", "Bearer " + newAccessToken);

                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            return true; // 의미 없는 반환값, 흐름 유지를 위해 사용
                        });

            }
        }else{

            CookieUtil.getCookieValue(request, "refreshToken")
                    .filter(jwtUtil::validateRefreshToken)
                    .map(jwtUtil::getUsernameFromRefreshToken)
                    .map(username -> {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        Long userId = ((User) userDetails).getId();

                        String newAccessToken = jwtUtil.generateAccessToken(username,userId);
                        response.setHeader("Authorization", "Bearer " + newAccessToken);

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(username, null,userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        return true; // 의미 없는 반환값, 흐름 유지를 위해 사용
                    });

        }

        chain.doFilter(request,response);

    }


}
