package com.plink.swfsys.piil.service.common.data.field;

public class StringField implements Field<String> {

    private String data;

    public StringField(String data) {
        this.data = data;
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.String;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String getData() {
        return data;
    }
}
