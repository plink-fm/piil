package com.plink.swfsys.piil.data.input.impl.field;

public class FieldFactory {
    /*
    public Polygon getPolygon(int numberOfSides) {
        if(numberOfSides == 3) {
            return new Triangle();
        }
        if(numberOfSides == 4) {
            return new Square();
        }
        if(numberOfSides == 5) {
            return new Pentagon();
        }
        if(numberOfSides == 7) {
            return new Heptagon();
        }
        else if(numberOfSides == 8) {
            return new Octagon();
        }
        return null;
    }
    */

    public Field getField(String input, String type) {
        // TODO: validate type

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
