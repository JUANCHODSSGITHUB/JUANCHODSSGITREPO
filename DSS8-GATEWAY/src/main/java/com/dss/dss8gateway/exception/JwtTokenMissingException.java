package com.dss.dss8gateway.exception;

public class JwtTokenMissingException extends RuntimeException{
    public JwtTokenMissingException(String message){super(message);}
}
