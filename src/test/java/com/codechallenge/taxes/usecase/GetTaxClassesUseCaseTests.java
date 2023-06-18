package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.model.tax.TaxClassCollection;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GetTaxClassesUseCaseTests {

    private final GetTaxClassesUseCase getTaxClassesUseCase;

    @Autowired
    public GetTaxClassesUseCaseTests(GetTaxClassesUseCase getTaxClassesUseCase) {
        this.getTaxClassesUseCase = getTaxClassesUseCase;
    }

    @Test
    public void isTaxClassCollectionLoadedTest() throws UseCaseRunFailedException {
        TaxClassCollection taxClassCollection = getTaxClassesUseCase.run();
        assertFalse(taxClassCollection.getTaxClassList().isEmpty());
    }
}
