package com.dss.dss4msmoviev1.exception;

public class CannotDeleteMovieException extends RuntimeException{
    public CannotDeleteMovieException(String message){
        super(message);
    }
}
