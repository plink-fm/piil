package com.plink.swfsys.piil.service.common;

import com.plink.swfsys.piil.service.ProductRecordPostProcessor;
import com.plink.swfsys.piil.service.data.OutputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class DefaultProductRecordPostProcessor implements ProductRecordPostProcessor {

    @Override
    public void process(ProductRecord productRecord) {
        // TODO: persist ProductRecord
    }

    @Override
    public void process(OutputSpecification outputSpecification, ProductRecord productRecord) {
        // TODO: persist ProductRecord
    }
}
