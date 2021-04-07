package com.plink.swfsys.piil;

import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
public class ConfigProperties {

    private Integer chainId;
    private Integer storeId;
    private Double taxRate;

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public List<DefaultFixedWidthInputItemDescriptor> getFixedWidthInputItemDescriptors() {
        return fixedWidthInputItemDescriptors;
    }

    public void setFixedWidthInputItemDescriptors(List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors) {
        this.fixedWidthInputItemDescriptors = fixedWidthInputItemDescriptors;
    }

    private List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors;
}
