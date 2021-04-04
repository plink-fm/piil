package com.plink.swfsys.piil.data.input.impl.field;

public class IntegerField implements NumberField<Integer> {

    private Integer data;

    IntegerField(String data) {
        this.data = Integer.valueOf(data);
    }

    @Override
    public Integer getData() {
        return data;
    }
}
