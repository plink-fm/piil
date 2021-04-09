package com.plink.swfsys.piil.service.common.data.field;

public class IntegerField implements NumberField<Integer> {

    private Integer data;

    IntegerField(String data) {
        this.data = Integer.valueOf(data);
    }

    @Override
    public void setData(Number data) {
        this.data = (Integer) data;
    }

    @Override
    public Integer getData() {
        return data;
    }
}
