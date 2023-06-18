package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassRepository;
import com.codechallenge.taxes.dataaccess.repository.exception.DataAccessException;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.TaxClassCollection;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTaxClassesUseCase {
    private final TaxClassRepository taxClassRepository;
    private final ExceptionHandler exceptionHandler;

    @Autowired
    public GetTaxClassesUseCase(TaxClassRepository taxClassRepository, ExceptionHandler exceptionHandler) {
        this.taxClassRepository = taxClassRepository;
        this.exceptionHandler = exceptionHandler;
    }

    /**
     * Returns a collection of tax-classes
     *
     * @return tax-classes as an TaxClassCollection object
     * @throws UseCaseRunFailedException in case the tax-class data couldn't be accessed or read
     */
    public TaxClassCollection run() throws UseCaseRunFailedException {
        try {
            return this.taxClassRepository.findAll();
        } catch (DataAccessException e) {
            this.exceptionHandler.handle(e);
            throw new UseCaseRunFailedException(e);
        }
    }
}
