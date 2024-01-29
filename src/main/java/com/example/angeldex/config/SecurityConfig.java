package com.example.angeldex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

         httpSecurity = httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity = httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity = httpSecurity.sessionManagement(ss -> {
            ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        httpSecurity = httpSecurity.authorizeRequests(au -> {
            au.anyRequest().permitAll();
        });
        httpSecurity = httpSecurity.formLogin(AbstractHttpConfigurer::disable);
        httpSecurity = httpSecurity.httpBasic(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true));
               return httpSecurity.build();
    }
}
