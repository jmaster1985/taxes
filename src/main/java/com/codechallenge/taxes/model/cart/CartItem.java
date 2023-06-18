package com.codechallenge.taxes.model.cart;

import java.io.Serial;
import java.io.Serializable;

public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productTitle;
    private String taxClassKey;
    private Double netPrice;
    private Double grossPrice;
    private Double taxAmount;

    public CartItem() {

    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getTaxClassKey() {
        return taxClassKey;
    }

    public void setTaxClassKey(String taxClassKey) {
        this.taxClassKey = taxClassKey;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }
}
