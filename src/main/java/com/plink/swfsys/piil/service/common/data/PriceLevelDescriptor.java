package com.plink.swfsys.piil.service.common.data;

public class PriceLevelDescriptor {
    private PriceMode priceMode;
    private Double price;

    public PriceLevelDescriptor(PriceMode priceMode, Double price) {
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
