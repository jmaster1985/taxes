package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassDBRepository;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CalculateCartItemUseCaseTests {

    private final RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase;

    @Autowired
    public CalculateCartItemUseCaseTests(RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase) {
        this.roundUpCalculatedTaxUseCase = roundUpCalculatedTaxUseCase;
    }


    @Test
    public void calculateCartItemUseCaseTest() throws UseCaseRunFailedException {
        TaxClass importExemptTaxClass = new TaxClass();
        importExemptTaxClass.setKey("import_basic");
        importExemptTaxClass.setRate(0.15);
        importExemptTaxClass.setTitle("Imported goods which are also subject to taxes inland");

        CartItem cartItem = new CartItem();
        cartItem.setProductTitle("Imported Perfume");
        cartItem.setUnitNetPrice(27.99);
        cartItem.setTaxClassKey("import_basic");

        TaxClassDBRepository taxClassRepository = mock(TaxClassDBRepository.class);
        when(taxClassRepository.findOneByKey("import_basic")).thenReturn(importExemptTaxClass);

        ExceptionHandler exceptionHandler = mock(ExceptionHandler.class);

        CalculateCartItemUseCase calculateCartItemUseCase = new CalculateCartItemUseCase(taxClassRepository, exceptionHandler, this.roundUpCalculatedTaxUseCase);
        calculateCartItemUseCase.run(cartItem);

        assertEquals(4.2, cartItem.getTotalTaxAmount());
    }
}
