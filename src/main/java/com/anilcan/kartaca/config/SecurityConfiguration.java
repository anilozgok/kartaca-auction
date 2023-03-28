package com.anilcan.kartaca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.authorizeHttpRequests()
                           .requestMatchers("/api/user/register")
                           .permitAll()
                           .anyRequest()
                           .authenticated()
                           .and()
                           .formLogin()
                           .defaultSuccessUrl("/api/user", true)
                           .and()
                           .build();
    }

}
