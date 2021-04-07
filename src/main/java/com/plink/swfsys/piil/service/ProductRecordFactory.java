package com.plink.swfsys.piil.service;


import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public interface ProductRecordFactory {

    ProductRecord create(InputSpecification inputSpecification, InputItem inputItem);
}
