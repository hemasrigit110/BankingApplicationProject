package com.example.Apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//public class SecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
////                .csrf(ServerHttpSecurity.CsrfSpec::disable)
////                .authorizeExchange(exchanges -> exchanges
//////                        .pathMatchers("/auth/register", "/auth/login").permitAll()  // public endpoints
//////                        .pathMatchers("/account/**", "/transactions/**").authenticated()  // secured endpoints
//////                        .anyExchange().permitAll()
//////                )
////                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
////                .formLogin(ServerHttpSecurity.FormLoginSpec::disable);
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttprequests(auth ->  auth.anyRequest().permitAll()) // Allow all requests for now
//
//        return http.build();
//    }
//}

//@Bean
//public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//    http
//            .csrf(csrf -> csrf.disable())
//            .authorizeExchange(exchanges -> exchanges
//                    .anyExchange().permitAll()
//            );
//    return http.build();
//}
//}


@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**","/account/**","/transaction/**").permitAll() // Public endpoints
                        .anyExchange().permitAll()
                );
        return http.build();
    }
}
