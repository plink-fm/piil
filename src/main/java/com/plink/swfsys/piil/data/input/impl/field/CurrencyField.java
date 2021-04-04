package com.plink.swfsys.piil.data.input.impl.field;

public interface CurrencyField<T> extends Field {

    @Override
    default FieldType getFieldType() {
        return FieldType.Currency;
    }
}
