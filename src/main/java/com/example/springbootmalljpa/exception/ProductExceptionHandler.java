package com.example.springbootmalljpa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

/**
 * ClassName: ProductExceptionHandler
 * Package: com.example.springbootmalljpa.exception
 */
@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchProductId(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NoSuchElementException:" + exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> test(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MethodArgumentNotValidException");
    }

}
