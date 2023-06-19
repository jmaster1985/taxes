package com.codechallenge.taxes.controller;

import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.usecase.exception.InvalidInputException;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    private final ExceptionHandler exceptionHandler;

    public BaseController(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Generates a response in case the REST call wasn't successful:
     * on unknown causes: a generic 500 response with an empty body, handled with 'exceptionHandler'
     * on known logic-related causes: a proper 4XX response with a proper message in body, not handled with 'exceptionHandler'
     *
     * @param t Throwable which caused the unsuccessful response
     * @return ResponseEntity as output
     */
    protected ResponseEntity<Object> createUnsuccessfulResponse(Throwable t) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = null;

        if (t instanceof UseCaseRunFailedException) {
            if (t.getCause() instanceof InvalidInputException) {
                status = HttpStatus.BAD_REQUEST;
                message = t.getCause().getMessage();
            }
        } else {
            this.exceptionHandler.handle(t);
        }

        return new ResponseEntity<>(new UnsuccessfulResponse(message), status);
    }
}
