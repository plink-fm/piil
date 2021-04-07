package com.plink.swfsys.piil.service.common.data.field;

public class FieldFactory {

    public Field getField(String input, String type) {
        FieldType fieldType = FieldType.valueOf(type);

        switch(fieldType)
        {
            case String:
                return new StringField(input);
            case Number:
                return new IntegerField(input);
            case Currency:
                return new USDollarCurrencyField(input);
            case Flags:
                return new FlagsField(input);
            default:
                throw new IllegalStateException(String.format("unknown Field type %s", type));
        }
    }
}
