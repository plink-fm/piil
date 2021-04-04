package com.plink.swfsys.piil.data.input.impl.field;

public class TaxableFlagField extends FlagsField implements FlagIndex {

    public TaxableFlagField(String data) {
        super(data);
    }

    @Override
    public int getIndex() {
        return 5;
    }
}
