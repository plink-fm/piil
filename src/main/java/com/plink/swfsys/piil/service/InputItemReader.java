package com.plink.swfsys.piil.service;


import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;

public interface InputItemReader {

    InputItem readItem(InputSpecification inputSpecification, final String inputStr);
}
