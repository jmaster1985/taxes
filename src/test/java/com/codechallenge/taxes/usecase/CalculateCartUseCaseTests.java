package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.dataaccess.repository.TaxClassDBRepository;
import com.codechallenge.taxes.exceptionhandler.ExceptionHandler;
import com.codechallenge.taxes.model.cart.Cart;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.model.tax.TaxClass;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class CalculateCartUseCaseTests {
    private final RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase;

    @Autowired
    public CalculateCartUseCaseTests(RoundUpCalculatedTaxUseCase roundUpCalculatedTaxUseCase) {
        this.roundUpCalculatedTaxUseCase = roundUpCalculatedTaxUseCase;
    }

    @Test
    public void calculateCartTest() throws UseCaseRunFailedException {
        Cart cart = this.getCart();

        ExceptionHandler exceptionHandler = mock(ExceptionHandler.class);

        TaxClassDBRepository taxClassDBRepository = this.getMockTaxClassDBRepository();

        CalculateCartItemUseCase calculateCartItemUseCase = new CalculateCartItemUseCase(taxClassDBRepository, exceptionHandler, this.roundUpCalculatedTaxUseCase);
        CalculateCartUseCase calculateCartUseCase = new CalculateCartUseCase((calculateCartItemUseCase));

        calculateCartUseCase.run(cart);

        assertEquals(6.7, cart.getSalesTax());
        assertEquals(74.68, cart.getTotal());
    }

    private Cart getCart() {
        Cart cart = new Cart();

        CartItem cartItem1 = new CartItem();
        cartItem1.setProductTitle("Imported Perfume");
        cartItem1.setUnitNetPrice(27.99);
        cartItem1.setTaxClassKey("import_basic");

        CartItem cartItem2 = new CartItem();
        cartItem2.setProductTitle("Perfume");
        cartItem2.setUnitNetPrice(18.99);
        cartItem2.setTaxClassKey("basic");

        CartItem cartItem3 = new CartItem();
        cartItem3.setProductTitle("Headache Pills");
        cartItem3.setUnitNetPrice(9.75);
        cartItem3.setTaxClassKey("exempt");

        CartItem cartItem4 = new CartItem();
        cartItem4.setProductTitle("Imported Chocolates");
        cartItem4.setUnitNetPrice(11.25);
        cartItem4.setTaxClassKey("import_exempt");

        cart.getCartItemList().add(cartItem1);
        cart.getCartItemList().add(cartItem2);
        cart.getCartItemList().add(cartItem3);
        cart.getCartItemList().add(cartItem4);

        return cart;
    }

    private TaxClassDBRepository getMockTaxClassDBRepository() {
        TaxClassDBRepository taxClassDBRepository = mock(TaxClassDBRepository.class);
        when(taxClassDBRepository.findOneByKey("basic")).thenReturn(getTaxClassMap().get("basic"));
        when(taxClassDBRepository.findOneByKey("exempt")).thenReturn(getTaxClassMap().get("exempt"));
        when(taxClassDBRepository.findOneByKey("import_basic")).thenReturn(getTaxClassMap().get("import_basic"));
        when(taxClassDBRepository.findOneByKey("import_exempt")).thenReturn(getTaxClassMap().get("import_exempt"));

        return taxClassDBRepository;
    }

    private Map<String, TaxClass> getTaxClassMap() {
        Map<String, TaxClass> taxClassMap = new HashMap<>();

        TaxClass basicTaxClass = new TaxClass();
        basicTaxClass.setKey("basic");
        basicTaxClass.setRate(0.10);
        basicTaxClass.setTitle("Goods which are subject to taxes inland");

        TaxClass exemptTaxClass = new TaxClass();
        exemptTaxClass.setKey("exempt");
        exemptTaxClass.setRate(0.0);
        exemptTaxClass.setTitle("Goods which are exempted from taxation");

        TaxClass importBasicTaxClass = new TaxClass();
        importBasicTaxClass.setKey("import_basic");
        importBasicTaxClass.setRate(0.15);
        importBasicTaxClass.setTitle("Imported goods which are also subject to taxes inland");

        TaxClass importExemptTaxClass = new TaxClass();
        importExemptTaxClass.setKey("import_exempt");
        importExemptTaxClass.setRate(0.05);
        importExemptTaxClass.setTitle("Imported goods which are exempted from taxes inland");

        taxClassMap.put(basicTaxClass.getKey(), basicTaxClass);
        taxClassMap.put(exemptTaxClass.getKey(), exemptTaxClass);
        taxClassMap.put(importBasicTaxClass.getKey(), importBasicTaxClass);
        taxClassMap.put(importExemptTaxClass.getKey(), importExemptTaxClass);

        return taxClassMap;
    }
}
