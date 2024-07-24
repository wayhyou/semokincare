package com.semokincare.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ProductException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;


    public ProductException(String message, Throwable throwable, HttpStatus httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }
}
