package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.data.input.impl.field.*;

public class ProductRecordWriter {

    // TODO: is there a better name for this class? Writer might imply persistence

    public ProductRecord create(InputItem inputItem) {
        /*
            Product ID
            Product Description
            Regular Display Price (English-readable string of your choosing, e.g. "$1.00" or "3 for $1.00")
            Regular Calculator Price (price the calculator should use, rounded to 4 decimal places, half-down)
            Sale Display Price, if it exists (same format as regular display price string)
            Sale Calculator Price, if it exists
            Unit of Measure ("Each" or "Pound"). Weighted items are per pound
            Product size
            Tax rate
        */

        ProductRecord productRecord = new ProductRecord();

        productRecord.setProductId(((IntegerField) inputItem.getField("Product ID")).getData());
        productRecord.setProductDescription(((StringField) inputItem.getField("Product Description")).getData());

        boolean isTaxable = isTaxable(inputItem);
        Object breakPoint = isTaxable;

        // TODO: Formatted string:
//        productRecord.setRegularDisplayPrice(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: calculated double:
//        productRecord.setRegularCalculatorPrice(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: Formatted string:
//        productRecord.setSaleDisplayPrice(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: calculated double:
//        productRecord.setSaleCalculatorPrice(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: calculated UnitOfMeasure:
//        productRecord.setUnitOfMeasure(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: calculated double:
//        productRecord.setTaxRate(((StringField) inputItem.getField("Product Description")).getData());

        // TODO: set metadata properties on ProductRecord, e.g. createDate, updateDate, etc.

        return productRecord;
    }

    // TODO: This needs to move to another class:
    private boolean isTaxable(InputItem inputItem) {
        final String flags = ((FlagsField) inputItem.getField("Flags")).getData();
        final String flag = String.valueOf(flags.charAt(5 - 1));
        return flag.equals("Y");
    }

    // TODO: implement FlagDescriptor or move Flag field setting to earlier in the process
//    private boolean getTaxableFlag(InputItem inputItem, FlagDescriptor flagDescriptor) {
//        return getFlag(inputItem, flagDescriptor.getIndex());
//    }

    private boolean getFlag(InputItem inputItem, int index) {
        final String flags = ((FlagsField) inputItem.getField("Flags")).getData();
        final String flag = String.valueOf(flags.charAt(index - 1));
        return flag.equals("Y") ? true : false;  // 0-based index, subtract off 1
    }
}
