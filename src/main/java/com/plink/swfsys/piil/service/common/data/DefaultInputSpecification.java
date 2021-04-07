package com.plink.swfsys.piil.service.common.data;

import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;
import com.plink.swfsys.piil.service.data.InputSpecification;

import java.util.List;

public class DefaultInputSpecification implements InputSpecification {

    private Integer chainId;
    private Integer storeId;
    private Double taxRate;

    private List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors;

    public DefaultInputSpecification(Integer chainId, Integer storeId, Double taxRate, List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors) {
        this.chainId = chainId;
        this.storeId = storeId;
        this.taxRate = taxRate;
        this.fixedWidthInputItemDescriptors = fixedWidthInputItemDescriptors;
    }

    @Override
    public List<DefaultFixedWidthInputItemDescriptor> getInputItemDescriptors() {
        return fixedWidthInputItemDescriptors;
    }

    @Override
    public Integer getChainId() {
        return chainId;
    }

    @Override
    public Integer getStoreId() {
        return storeId;
    }

    @Override
    public Double getTaxRate() {
        return taxRate;
    }
}
