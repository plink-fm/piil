package com.plink.swfsys.piil.data.input.impl;

public class ProductRecord {

    // TODO: add metadata, like databaseId, createDate, updateDate, etc.

    private Integer productId;
    private String productDescription;
    private String regularDisplayPrice;

    private double regularCalculatorPrice;

    private String saleDisplayPrice;
    private double saleCalculatorPrice;

    private UnitOfMeasure unitOfMeasure;
    private double taxRate;

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

    public double getRegularCalculatorPrice() {
        return regularCalculatorPrice;
    }

    public void setRegularCalculatorPrice(double regularCalculatorPrice) {
        this.regularCalculatorPrice = regularCalculatorPrice;
    }

    public String getSaleDisplayPrice() {
        return saleDisplayPrice;
    }

    public void setSaleDisplayPrice(String saleDisplayPrice) {
        this.saleDisplayPrice = saleDisplayPrice;
    }

    public double getSaleCalculatorPrice() {
        return saleCalculatorPrice;
    }

    public void setSaleCalculatorPrice(double saleCalculatorPrice) {
        this.saleCalculatorPrice = saleCalculatorPrice;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}
