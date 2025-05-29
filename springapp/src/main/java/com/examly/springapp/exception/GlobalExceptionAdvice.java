package com.examly.springapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<?> userExistHandler(UserExistException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(CharityAlreadyExistsException.class)
    public ResponseEntity<?> handleCharityAlreadyExistsException(CharityAlreadyExistsException e){
        return ResponseEntity.status(500).body(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class ) 
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) { 
        String errmsg="Invalid Credential!! Authentication Failed"; 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errmsg); 
    } 
    
}
