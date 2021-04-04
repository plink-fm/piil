package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.data.input.InputItemDescriptor;

import java.util.List;

public class InputItemTransformer {

    // TODO: may not need these anymore:
    private List<InputItemDescriptor> inputItemDescriptors;

    public InputItemTransformer(List<InputItemDescriptor> inputItemDescriptors) {
        this.inputItemDescriptors = inputItemDescriptors;
    }

    public void transform(InputItem inputItem) {
        // TODO: may want to have multiple transformers, and multiple per-field ???
    }
}
