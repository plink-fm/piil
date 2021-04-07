package com.plink.swfsys.piil.chain_x.handler;

import com.plink.swfsys.piil.service.common.data.field.FieldType;
import com.plink.swfsys.piil.service.common.data.field.StringField;
import com.plink.swfsys.piil.service.InputItemHandler;
import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

import java.util.List;

public class ChainXStringHandler implements InputItemHandler {

    @Override
    public void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        trimSStringFieldValues(inputSpecification, inputItem);
    }

    protected void trimSStringFieldValues(InputSpecification inputSpecification, InputItem inputItem) {
        final List<DefaultFixedWidthInputItemDescriptor> inputItemDescriptors = inputSpecification.getInputItemDescriptors();
        if (inputItemDescriptors == null || inputItemDescriptors.size() == 0) {
            return;
        }

        for (DefaultFixedWidthInputItemDescriptor inputItemDescriptor : inputItemDescriptors) {
            if (inputItemDescriptor.getType().equals(FieldType.String.toString())) {
                final StringField field = (StringField) inputItem.getField(inputItemDescriptor.getName());
                trimFieldValue(field);
            }
        }
    }

    protected void trimFieldValue(StringField field) {
        field.setData(field.getData() != null ? field.getData().trim() : "");
    }
}
