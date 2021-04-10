package com.plink.swfsys.piil.chain_x.data;

import com.plink.swfsys.piil.service.common.data.DefaultInputSpecification;
import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;

import java.util.List;

public class ChainXInputSpecification extends DefaultInputSpecification {

    private Integer perWeightFlagIndex;
    private Integer taxableFlagIndex;

    public ChainXInputSpecification(Integer chainId,
                                    Integer storeId,
                                    Double taxRate,
                                    List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors,
                                    Integer perWeightFlagIndex,
                                    Integer taxableFlagIndex) {

        super(chainId, storeId, taxRate, fixedWidthInputItemDescriptors);
        this.perWeightFlagIndex = perWeightFlagIndex;
        this.taxableFlagIndex = taxableFlagIndex;
    }

    public Integer getPerWeightFlagIndex() {
        return perWeightFlagIndex;
    }

    public Integer getTaxableFlagIndex() {
        return taxableFlagIndex;
    }


}
