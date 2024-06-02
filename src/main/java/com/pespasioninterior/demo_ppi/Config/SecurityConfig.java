package com.pespasioninterior.demo_ppi.Config;

import com.pespasioninterior.demo_ppi.Security.Jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;
    
    @Bean
    public SecurityFilterChain securityFiterlChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf ->
                    csrf.disable()
                )
                .authorizeHttpRequests(authRequest ->
                    authRequest
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                    sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
                
//  TODO EL CÓDIGO DE ABAJO, ES PARA CONFIGURAR SOLAMENTE LOS ACCESOS PUBLICOS Y PRIVADOS
//  UTILIZANDO EL FORM LOGIN POR DEFECTO DE SPRING                
//                .csrf(csrf ->
//                    csrf.disable()
//                )
//                .authorizeHttpRequests(authRequest ->
//                    authRequest
//                        .requestMatchers("/auth/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .build();
    }
}