package com.semokincare.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object>
            handleProductNotFoundException(ProductNotFoundException productNotFoundException) {

        ProductException productException = new ProductException(
                productNotFoundException.getMessage(),
                productNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(productException, HttpStatus.NOT_FOUND);
    }
}
