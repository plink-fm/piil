package com.plink.swfsys.piil.service.common.data.fixedwidth;

import com.plink.swfsys.piil.service.common.data.field.Field;
import com.plink.swfsys.piil.service.common.data.field.FieldFactory;
import com.plink.swfsys.piil.service.InputItemReader;
import com.plink.swfsys.piil.service.common.data.DefaultInputItem;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputItemDescriptor;
import com.plink.swfsys.piil.service.data.InputSpecification;

import java.util.List;

public class FixedWidthStringInputItemReader implements InputItemReader {

    private FieldFactory fieldFactory = new FieldFactory();

    @Override
    public InputItem readItem(InputSpecification inputSpecification, String inputStr) {
        if (inputStr == null) {
            return null;
        }

        List<DefaultFixedWidthInputItemDescriptor> inputItemDescriptors = inputSpecification.getInputItemDescriptors();

        InputItem inputItem = new DefaultInputItem();

        // loop through inputItemDescriptors and create a list of Field objects of concrete types
        for (InputItemDescriptor inputItemDescriptor : inputItemDescriptors) {

            // Note:  descriptor item for start/end are one- based
            String data;

            try {
                data = inputStr.substring(
                        ((FixedWidthInputItemDescriptor) inputItemDescriptor).getStart() - 1,
                        ((FixedWidthInputItemDescriptor) inputItemDescriptor).getEnd());

                Field field = fieldFactory.getField(data, inputItemDescriptor.getType());
                inputItem.addField(inputItemDescriptor.getName(), field);
            }
            catch (Exception e) {
                // TODO: capture and aggregate error items
                System.out.println(String.format("Error reading item: %s", inputStr));
                e.printStackTrace();
                return null;
            }
        }

        return inputItem;
    }
}
