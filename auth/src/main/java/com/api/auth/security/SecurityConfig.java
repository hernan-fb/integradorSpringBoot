package com.api.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //proporciona configuración a nivel contenedor
@EnableWebSecurity // Habilita la seguridad en el contenedor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // esto es algo impotante Deshabilita CSRF (Cross-Site)
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest()
                                .permitAll()
                );
        return httpSecurity.build();
    }

}
