package com.plink.swfsys.piil.chain_x.handler;

import com.plink.swfsys.piil.chain_x.data.ChainXInputSpecification;
import com.plink.swfsys.piil.service.common.data.UnitOfMeasure;
import com.plink.swfsys.piil.service.common.data.field.StringField;
import com.plink.swfsys.piil.service.InputItemHandler;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class ChainXUnitOfMeasureHandler implements InputItemHandler {

    @Override
    public void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        setUnitOfMeasureInner(inputSpecification, inputItem, productRecord);
    }

    protected void setUnitOfMeasureInner(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        final String flags = ((StringField) inputItem.getField("Flags")).getData();

        // TODO: push flag indices down into flag item descriptors
        final String flag = String.valueOf(flags.charAt(((ChainXInputSpecification) inputSpecification).getPerWeightFlagIndex() - 1));

        boolean isPerWeight = flag.equals("Y");

        ((DefaultProductRecord) productRecord).setUnitOfMeasure(
                isPerWeight ? UnitOfMeasure.Pound.toString() : UnitOfMeasure.Each.toString());

    }
}
