package com.dss.dss8gateway.exception;

public class JwtTokenMalformedException extends RuntimeException{
    public JwtTokenMalformedException(String message){super(message);}
}
