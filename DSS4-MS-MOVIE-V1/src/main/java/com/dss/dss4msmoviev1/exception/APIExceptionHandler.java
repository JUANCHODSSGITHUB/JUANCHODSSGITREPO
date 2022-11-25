package com.dss.dss4msmoviev1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value = {MovieNotFoundException.class})
    public ResponseEntity<Object> conflictRequestException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CannotDeleteMovieException.class})
    public ResponseEntity<Object> loginException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

}
