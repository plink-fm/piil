package com.plink.swfsys.piil.data.input.impl;

public abstract class PriceLevelBase {
    private PriceMode priceMode;
    private Double price;

    public PriceLevelBase(PriceMode priceMode, Double price) {
        this.priceMode = priceMode;
        this.price = price;
    }

    public PriceMode getPriceMode() {
        return priceMode;
    }

    public Double getPrice() {
        return price;
    }
}
