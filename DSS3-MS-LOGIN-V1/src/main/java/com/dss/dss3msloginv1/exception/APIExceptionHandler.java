package com.dss.dss3msloginv1.exception;

import com.dss.dss3msloginv1.dto.RequestErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.BAD_REQUEST);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage("Malformed JSON request");
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AdminAlreadyExistsException.class})
    public ResponseEntity<Object> conflictRequestException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.CONFLICT);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {LoginFailedException.class})
    public ResponseEntity<Object> loginException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.FORBIDDEN);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<Object> invalidInputException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> deleteException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.NOT_FOUND);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.NOT_FOUND);
    }

}
