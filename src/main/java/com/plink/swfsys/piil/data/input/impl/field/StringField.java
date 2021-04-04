package com.plink.swfsys.piil.data.input.impl.field;

public class StringField implements Field<String> {

    private String data;

    StringField(String data) {
        this.data = data;
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.String;
    }

    @Override
    public String getData() {
        return data;
    }
}
