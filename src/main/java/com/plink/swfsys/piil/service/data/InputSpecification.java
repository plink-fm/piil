package com.plink.swfsys.piil.service.data;

import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;

import java.util.List;

public interface InputSpecification {

    List<DefaultFixedWidthInputItemDescriptor> getInputItemDescriptors();

    Integer getChainId();

    Integer getStoreId();

    Double getTaxRate();
}
