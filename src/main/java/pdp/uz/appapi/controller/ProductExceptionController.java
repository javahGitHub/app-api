package pdp.uz.appapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionController {



    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity exception(ProductNotfoundException exception) {
        return new ResponseEntity("Product not found", HttpStatus.CONFLICT);
    }

}