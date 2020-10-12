package com.banco.conta.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    public JwtAuthFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            CredenciaisDto credenciaisDto = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDto.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( 
                credenciaisDto.getEmail(), credenciaisDto.getPassword(), new ArrayList<>()
            );
            Authentication authentication = authenticationManager.authenticate(token);
            return authentication;
        } catch (IOException e) {
            throw new RuntimeException(e);
        
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        String userName = ((ClienteSecurity)authResult.getPrincipal()).getUsername();
        String token = jwtUtil.gerarToken(userName);
        response.addHeader("Authorization", "Bearer " + token);
    }
}
