package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.model.cart.Cart;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateCartUseCase {
    private final CalculateCartItemUseCase calculateCartItemUseCase;

    @Autowired
    public CalculateCartUseCase(CalculateCartItemUseCase calculateCartItemUseCase) {
        this.calculateCartItemUseCase = calculateCartItemUseCase;
    }

    public Cart run(Cart cart) throws UseCaseRunFailedException {
        Double total = 0.0;
        Double salesTax = 0.0;

        for (CartItem cartItem : cart.getCartItemList()) {
            this.calculateCartItemUseCase.run(cartItem);

            total = total + cartItem.getTotalGrossPrice();
            salesTax = salesTax + cartItem.getTotalTaxAmount();
        }

        cart.setTotal(SanitizeDoubleValueUseCase.run(total));
        cart.setSalesTax(SanitizeDoubleValueUseCase.run(salesTax));

        return cart;
    }
}
