package com.codechallenge.taxes.model.cart;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productTitle;
    private String taxClassKey;
    private Double unitNetPrice;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Double unitGrossPrice;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Double unitTaxAmount;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Double totalGrossPrice;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Double totalTaxAmount;
    private Integer quantity = 1;

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

    public Double getUnitNetPrice() {
        return unitNetPrice;
    }

    public void setUnitNetPrice(Double unitNetPrice) {
        this.unitNetPrice = unitNetPrice;
    }

    public Double getUnitGrossPrice() {
        return unitGrossPrice;
    }

    public void setUnitGrossPrice(Double unitGrossPrice) {
        this.unitGrossPrice = unitGrossPrice;
    }

    public Double getUnitTaxAmount() {
        return unitTaxAmount;
    }

    public void setUnitTaxAmount(Double unitTaxAmount) {
        this.unitTaxAmount = unitTaxAmount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalGrossPrice() {
        return totalGrossPrice;
    }

    public void setTotalGrossPrice(Double totalGrossPrice) {
        this.totalGrossPrice = totalGrossPrice;
    }

    public Double getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(Double totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }
}
