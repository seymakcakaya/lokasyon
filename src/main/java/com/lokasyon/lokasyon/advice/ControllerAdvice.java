package com.lokasyon.lokasyon.advice;


import com.lokasyon.lokasyon.exception.NeighborhoodNotFoundException;
import com.lokasyon.lokasyon.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ControllerAdvice {


    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<String> notFoundHatasi(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NeighborhoodNotFoundException.class})
    public ResponseEntity<String> NeighborhoodNotFoundException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

}

