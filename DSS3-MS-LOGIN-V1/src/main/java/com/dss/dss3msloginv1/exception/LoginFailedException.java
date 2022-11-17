package com.dss.dss3msloginv1.exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException(String message){
        super(message);
    }
}
