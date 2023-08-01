package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassDBRepository;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.model.tax.TaxClassCollection;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetTaxClassesUseCase {
    private final TaxClassDBRepository taxClassDBRepository;
    private final ExceptionHandler exceptionHandler;

    @Autowired
    public GetTaxClassesUseCase(
            ExceptionHandler exceptionHandler,
            TaxClassDBRepository taxClassDBRepository
    ) {
        this.exceptionHandler = exceptionHandler;
        this.taxClassDBRepository = taxClassDBRepository;
    }

    /**
     * Returns a collection of tax-classes
     *
     * @return tax-classes as an TaxClassCollection object
     * @throws UseCaseRunFailedException in case the tax-class data couldn't be accessed or read
     */
    public TaxClassCollection run() throws UseCaseRunFailedException {
        try {
            Iterable<TaxClass> taxClassIterable = this.taxClassDBRepository.findAll();
            TaxClassCollection taxClassCollection = new TaxClassCollection();

            for(TaxClass taxClass : taxClassIterable) {
                taxClassCollection.addTaxClass(taxClass);
            }

            return taxClassCollection;
        } catch (Throwable t) {
            this.exceptionHandler.handle(t);
            throw new UseCaseRunFailedException(t);
        }
    }
}
