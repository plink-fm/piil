package com.plink.swfsys.piil.service.common.data.field;

public interface CurrencyField<T> extends Field {

    @Override
    default FieldType getFieldType() {
        return FieldType.Currency;
    }

}
