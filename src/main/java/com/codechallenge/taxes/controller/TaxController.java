package com.codechallenge.taxes.controller;

import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.usecase.GetTaxClassesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController extends BaseController {
    private final GetTaxClassesUseCase getTaxClassesUseCase;

    @Autowired
    public TaxController(ExceptionHandler exceptionHandler, GetTaxClassesUseCase getTaxClassesUseCase) {
        super(exceptionHandler);
        this.getTaxClassesUseCase = getTaxClassesUseCase;
    }

    @GetMapping(value = "/tax/class", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaxClasses() {
        try {
            return new ResponseEntity<>(this.getTaxClassesUseCase.run().getTaxClassList(), HttpStatus.OK);
        } catch (Throwable t) {
            return createUnsuccessfulResponse(t);
        }
    }
}
