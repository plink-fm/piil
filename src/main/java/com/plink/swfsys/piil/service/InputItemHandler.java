package com.plink.swfsys.piil.service;


import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

/**
 * Handler plugin for calculating/mutating ProductRecord data.
 *
 * Note that handlers may depend on multiple fields for calculation. As such transformers are not run until after
 * all input fields have been read.
 */
public interface InputItemHandler {

    void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord);
}
