package com.dss.dss5msactorv1.exception;


import com.dss.dss5msactorv1.dto.RequestErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DssExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.BAD_REQUEST);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage("Malformed JSON request");
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ActorNotFoundException.class})
    public ResponseEntity<Object> notFoundException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.NOT_FOUND);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {CannotDeleteActorException.class})
    public ResponseEntity<Object> cannotDeleteException(Exception exception) {
        RequestErrorDTO requestErrorDTO = new RequestErrorDTO();
        requestErrorDTO.setStatus(HttpStatus.CONFLICT);
        requestErrorDTO.setTimestamp(LocalDateTime.now());
        requestErrorDTO.setMessage(exception.getMessage());
        return new ResponseEntity<>(requestErrorDTO, HttpStatus.CONFLICT);
    }
}
