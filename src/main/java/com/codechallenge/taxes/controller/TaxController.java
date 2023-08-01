package com.codechallenge.taxes.controller;

import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.cart.Cart;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.usecase.CalculateCartUseCase;
import com.codechallenge.taxes.usecase.CreateTaxClassUseCase;
import com.codechallenge.taxes.usecase.GetTaxClassesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController extends BaseController {
    private final GetTaxClassesUseCase getTaxClassesUseCase;
    private final CalculateCartUseCase calculateCartUseCase;
    private final CreateTaxClassUseCase createTaxClassUseCase;

    @Autowired
    public TaxController(
            ExceptionHandler exceptionHandler,
            GetTaxClassesUseCase getTaxClassesUseCase,
            CalculateCartUseCase calculateCartUseCase,
            CreateTaxClassUseCase createTaxClassUseCase
    ) {
        super(exceptionHandler);
        this.getTaxClassesUseCase = getTaxClassesUseCase;
        this.calculateCartUseCase = calculateCartUseCase;
        this.createTaxClassUseCase = createTaxClassUseCase;
    }

    @GetMapping(value = "/tax/class", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTaxClasses() {
        try {
            return new ResponseEntity<>(this.getTaxClassesUseCase.run().getTaxClassList(), HttpStatus.OK);
        } catch (Throwable t) {
            return createUnsuccessfulResponse(t);
        }
    }

    @PostMapping(value = "/tax/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> calculateTax(@RequestBody Cart cart) {
        try {
            return new ResponseEntity<>(this.calculateCartUseCase.run(cart), HttpStatus.OK);
        } catch (Throwable t) {
            return createUnsuccessfulResponse(t);
        }
    }

    @PostMapping(value = "/tax/class", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createTaxClass(@RequestBody TaxClass taxClass) {
        try {
            this.createTaxClassUseCase.run(taxClass);
            return new ResponseEntity<>(taxClass, HttpStatus.OK);
        } catch (Throwable t) {
            return createUnsuccessfulResponse(t);
        }
    }

}
