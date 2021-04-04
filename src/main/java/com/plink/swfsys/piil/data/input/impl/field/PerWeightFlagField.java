package com.plink.swfsys.piil.data.input.impl.field;

public class PerWeightFlagField extends FlagsField implements FlagIndex {

    public PerWeightFlagField(String data) {
        super(data);
    }

    @Override
    public int getIndex() {
        return 3;
    }

//    @Override
//    public String getData() {
//        return String.valueOf(super.getData().charAt(getIndex() - 1));
//    }
}
