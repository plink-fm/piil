package com.plink.swfsys.piil.service.common.data.field;

public interface NumberField<T> extends Field<Number> {

    @Override
    default FieldType getFieldType() {
        return FieldType.Number;
    }
}
