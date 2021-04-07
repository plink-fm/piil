package com.plink.swfsys.piil.service;

import com.plink.swfsys.piil.service.data.OutputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public interface ProductRecordPostProcessor {

    // Marker interface for now

    void process(ProductRecord productRecord);

    void process(OutputSpecification outputSpecification, ProductRecord productRecord);
}
