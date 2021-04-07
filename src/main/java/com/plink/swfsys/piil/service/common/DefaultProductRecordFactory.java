package com.plink.swfsys.piil.service.common;

import com.plink.swfsys.piil.service.ProductRecordFactory;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class DefaultProductRecordFactory implements ProductRecordFactory {

    @Override
    public ProductRecord create(InputSpecification inputSpecification, InputItem inputItem) {
        DefaultProductRecord productRecord = new DefaultProductRecord();
        productRecord.setChainId(inputSpecification.getChainId());
        productRecord.setStoreId(inputSpecification.getStoreId());
        return productRecord;
    }
}
