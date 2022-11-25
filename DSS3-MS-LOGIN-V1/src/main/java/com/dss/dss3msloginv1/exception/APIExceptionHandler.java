package com.dss.dss3msloginv1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {



    @ExceptionHandler(value = {AdminAlreadyExistsException.class})
    public ResponseEntity<Object> conflictRequestException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {LoginFailedException.class, InvalidInputException.class})
    public ResponseEntity<Object> loginException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> deleteException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}
