package com.examly.springapp.exception;

public class CharityAlreadyExistsException extends RuntimeException{
    public CharityAlreadyExistsException(String msg){
        super(msg);
    }
}
