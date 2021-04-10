package com.plink.swfsys.piil.chain_x.handler;

import com.plink.swfsys.piil.chain_x.data.ChainXInputSpecification;
import com.plink.swfsys.piil.service.InputItemHandler;
import com.plink.swfsys.piil.service.common.data.field.StringField;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class ChainXTaxHandler implements InputItemHandler {

    @Override
    public void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        setTaxRateInner(inputSpecification, inputItem, productRecord, inputSpecification.getTaxRate());
    }

    protected void setTaxRateInner(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord, Double globalTaxRate) {
        final String flags = ((StringField) inputItem.getField("Flags")).getData();

        // TODO: push flag indices down into flag item descriptors
        final String flag = String.valueOf(flags.charAt(((ChainXInputSpecification) inputSpecification).getTaxableFlagIndex() - 1));

        boolean isTaxable = flag.equals("Y");

        ((DefaultProductRecord) productRecord).setTaxRate(isTaxable ? globalTaxRate.toString() : "0.00");
    }
}
