package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.data.input.InputItemDescriptor;
import com.plink.swfsys.piil.data.input.impl.field.Field;
import com.plink.swfsys.piil.data.input.impl.field.FieldFactory;

import java.util.List;

public class InputItemReader {

    private FieldFactory fieldFactory = new FieldFactory();

    private List<InputItemDescriptor> inputItemDescriptors;

    public InputItemReader(List<InputItemDescriptor> inputItemDescriptors) {
        this.inputItemDescriptors = inputItemDescriptors;
    }

    public InputItem readItem(final String inputStr) {
        if (inputStr == null) {
            return null;
        }

        // TODO: this class InputItemReader should be subclassed for this data provider.  The way that flags
        // are handled, for example, is specific to this store's format

//        List<Field> fields = new ArrayList<>(inputItemDescriptors.size());

        InputItem inputItem = new InputItem();

        // loop through inputItemDescriptors and create a list of FieldReaders of concrete types
        for (InputItemDescriptor inputItemDescriptor : inputItemDescriptors) {

            // TODO:  add config item for whether start/end are zero- or one-index based
            String data = inputStr.substring(inputItemDescriptor.getStart() - 1, inputItemDescriptor.getEnd());
            Field field = fieldFactory.getField(data, inputItemDescriptor.getType());
//            fields.add(field);

            inputItem.addField(inputItemDescriptor.getName(), field);
        }

        return inputItem;
    }
}
