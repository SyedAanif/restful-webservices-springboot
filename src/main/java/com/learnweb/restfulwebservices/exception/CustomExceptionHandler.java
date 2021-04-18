package com.learnweb.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), userNotFoundException.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException){
        String defaultMessage = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage()).collect(Collectors.joining(", "))+".";
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Failed.", defaultMessage);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
