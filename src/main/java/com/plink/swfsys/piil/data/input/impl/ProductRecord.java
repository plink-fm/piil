package com.plink.swfsys.piil.data.input.impl;

public class ProductRecord {

    // TODO: add metadata, like databaseId, createDate, updateDate, etc.

    private Integer productId;
    private String productDescription;
    private String regularDisplayPrice;

    private String regularCalculatorPrice;

    private String saleDisplayPrice;
    private String saleCalculatorPrice;

    private UnitOfMeasure unitOfMeasure;
    private String taxRate;

    // TODO: use Lombok?

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getRegularDisplayPrice() {
        return regularDisplayPrice;
    }

    public void setRegularDisplayPrice(String regularDisplayPrice) {
        this.regularDisplayPrice = regularDisplayPrice;
    }

    public String getRegularCalculatorPrice() {
        return regularCalculatorPrice;
    }

    public String setRegularCalculatorPrice(String regularCalculatorPrice) {
        return this.regularCalculatorPrice = regularCalculatorPrice;
    }

    public String getSaleDisplayPrice() {
        return saleDisplayPrice;
    }

    public void setSaleDisplayPrice(String saleDisplayPrice) {
        this.saleDisplayPrice = saleDisplayPrice;
    }

    public String getSaleCalculatorPrice() {
        return saleCalculatorPrice;
    }

    public void setSaleCalculatorPrice(String saleCalculatorPrice) {
        this.saleCalculatorPrice = saleCalculatorPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }
}
