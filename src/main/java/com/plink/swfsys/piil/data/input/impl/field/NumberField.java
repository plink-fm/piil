package com.plink.swfsys.piil.data.input.impl.field;

public interface NumberField<T> extends Field {

    @Override
    default FieldType getFieldType() {
        return FieldType.Number;
    }
}
