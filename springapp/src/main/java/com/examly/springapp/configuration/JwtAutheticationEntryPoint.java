package com.examly.springapp.configuration;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.security.core.AuthenticationException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAutheticationEntryPoint implements AuthenticationEntryPoint  { 

    @Autowired
    @Qualifier("handlerExceptionResolver") 
    private HandlerExceptionResolver resolver; 

    @Override 
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException { 
        resolver.resolveException(request, response, null, authException); 
    } 
}
