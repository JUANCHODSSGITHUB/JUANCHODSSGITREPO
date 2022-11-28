package com.dss.dss5msactorv1.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DssExceptionHandler {

    @ExceptionHandler(value = {ActorNotFoundException.class})
    public ResponseEntity<Object> conflictRequestException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CannotDeleteActorException.class})
    public ResponseEntity<Object> loginException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
