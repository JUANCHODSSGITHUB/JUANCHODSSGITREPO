package com.dss.dss5msactorv1.exception;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(String message){
        super(message);
    }
}
