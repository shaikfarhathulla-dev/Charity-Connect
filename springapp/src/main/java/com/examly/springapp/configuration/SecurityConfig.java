package com.examly.springapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired 
    UserDetailsService userDetailsService; 

    @Autowired 
    JwtAuthenticationFilter jwtAuthenticationFilter; 

    @Autowired 
    JwtAutheticationEntryPoint unauthorizedHandler;

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { 
        httpSecurity.csrf(csrf -> csrf.disable()).cors(); 
        httpSecurity.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest 
                    .requestMatchers("/api/login","/api/register","/api/charities","/api/donations").permitAll() 
          			.anyRequest().authenticated() 
                //    .requestMatchers("/api/gift","/api/gift/**") 
                //    .authenticated()
                    ); 

            httpSecurity.sessionManagement(sessionManager  -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 
            httpSecurity.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler::commence)); 
            httpSecurity.authenticationProvider(authenticationProvider()); 
            httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 
        return httpSecurity.build(); 
    } 


    // @Bean 
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
    //     return http.csrf().disable() 
    //                 .authorizeHttpRequests() 
    //                 .requestMatchers("/auth/login","/auth/register","/api/gift/welcome") 
    //                 .permitAll() 
    //                 .and() 
    //                 .authorizeHttpRequests() 
    //                 .requestMatchers("/api/gift/**") 
    //                 .authenticated() 
    //                 .and() 
    //                 .sessionManagement() 
    //                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
    //                 .and() 
    //                 .exceptionHandling().authenticationEntryPoint(unauthorizedHandler) 
    //                 .and() 
    //                 .authenticationProvider(authenticationProvider()) 
    //                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build(); 
    // } 

    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 

    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager();
    } 
}
