package com.codechallenge.taxes.model.cart;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Double salesTax = 0.0;
    private Double total = 0.0;
    private List<CartItem> cartItemList;

    public Cart() {
        cartItemList = new ArrayList<>();
    }

    public Double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(Double salesTax) {
        this.salesTax = salesTax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
