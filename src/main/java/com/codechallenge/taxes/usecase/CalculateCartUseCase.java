package com.codechallenge.taxes.usecase;

import com.codechallenge.taxes.model.cart.Cart;
import com.codechallenge.taxes.model.cart.CartItem;
import com.codechallenge.taxes.usecase.exception.UseCaseRunFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

        BigDecimal totalAsBigDecimal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
        BigDecimal salesTaxBigDecimal = new BigDecimal(salesTax).setScale(2, RoundingMode.HALF_UP);

        cart.setTotal(totalAsBigDecimal.doubleValue());
        cart.setSalesTax(salesTaxBigDecimal.doubleValue());

        return cart;
    }
}
