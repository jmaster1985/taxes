package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.model.cart.Cart;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CalculateCartUseCaseTests {
    private final CalculateCartUseCase calculateCartUseCase;

    @Autowired
    public CalculateCartUseCaseTests(CalculateCartUseCase calculateCartUseCase) {
        this.calculateCartUseCase = calculateCartUseCase;
    }

    @Test
    public void calculateCartTest() throws UseCaseRunFailedException {
        Cart cart = new Cart();

        CartItem cartItem1 = new CartItem();
        cartItem1.setProductTitle("Imported Perfume");
        cartItem1.setNetPrice(27.99);
        cartItem1.setTaxClassKey("import_basic");

        CartItem cartItem2 = new CartItem();
        cartItem2.setProductTitle("Perfume");
        cartItem2.setNetPrice(18.99);
        cartItem2.setTaxClassKey("basic");

        CartItem cartItem3 = new CartItem();
        cartItem3.setProductTitle("Headache Pills");
        cartItem3.setNetPrice(9.75);
        cartItem3.setTaxClassKey("exempt");

        CartItem cartItem4 = new CartItem();
        cartItem4.setProductTitle("Imported Chocolates");
        cartItem4.setNetPrice(11.25);
        cartItem4.setTaxClassKey("import_exempt");

        cart.getCartItemList().add(cartItem1);
        cart.getCartItemList().add(cartItem2);
        cart.getCartItemList().add(cartItem3);
        cart.getCartItemList().add(cartItem4);

        this.calculateCartUseCase.run(cart);

        assertEquals(6.7, cart.getSalesTax());
        assertEquals(74.68, cart.getTotal());
    }
}
