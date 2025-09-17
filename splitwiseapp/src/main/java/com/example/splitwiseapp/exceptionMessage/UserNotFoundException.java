package com.example.splitwiseapp.exceptionMessage;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg){
        super(msg);
    }
}
