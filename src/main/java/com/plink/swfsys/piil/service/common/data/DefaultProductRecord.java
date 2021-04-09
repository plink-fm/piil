package com.plink.swfsys.piil.service.common.data;

import com.plink.swfsys.piil.service.data.ProductRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultProductRecord implements ProductRecord {

    private static final String LEADING_DELIMITER = ": ";
    private static final String TRAILING_DELIMITER = "  ";

    private Integer chainId;
    private Integer storeId;
    private LocalDateTime createDate;

    private Integer productId;
    private String productDescription;
    private String regularDisplayPrice;

    private String regularCalculatorPrice;

    private String saleDisplayPrice;
    private String saleCalculatorPrice;

    private String unitOfMeasure;
    private String taxRate;

    public DefaultProductRecord() {
        this.createDate = LocalDateTime.now();
    }

    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    @Override
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append(productId);
        sb.append(TRAILING_DELIMITER);
        sb.append(productDescription);
        sb.append(TRAILING_DELIMITER);

        wrapToStringItem(sb, "RegDisplayPrice", regularDisplayPrice);
        wrapToStringItem(sb, "RegCalcPrice", regularCalculatorPrice);
        wrapToStringItem(sb, "SaleDisplayPrice", saleDisplayPrice);
        wrapToStringItem(sb, "SaleCalcPrice", saleCalculatorPrice);
        wrapToStringItem(sb, "Unit", unitOfMeasure);
        wrapToStringItem(sb, "TaxRate", taxRate);

        // Metadata
        wrapToStringItem(sb, "chain", chainId.toString());
        wrapToStringItem(sb, "store", storeId.toString());
        wrapToStringItem(sb, "createDate", createDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return sb.toString();
    }

    private void wrapToStringItem(StringBuilder sb, String name, String value) {
        sb.append(name);
        sb.append(LEADING_DELIMITER);
        sb.append(value);
        sb.append(TRAILING_DELIMITER);
    }
}
