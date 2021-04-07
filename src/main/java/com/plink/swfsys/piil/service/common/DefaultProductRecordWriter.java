package com.plink.swfsys.piil.service.common;

import com.plink.swfsys.piil.service.common.data.field.IntegerField;
import com.plink.swfsys.piil.service.common.data.field.StringField;
import com.plink.swfsys.piil.service.ProductRecordWriter;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class DefaultProductRecordWriter implements ProductRecordWriter {

    // TODO: is there a better name for this class? Writer might imply persistence

    @Override
    public void write(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {

        /*
            Chain ID
            Store ID
            ----------------
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

        productRecord.setProductId(((IntegerField) inputItem.getField("Product ID")).getData());
        productRecord.setProductDescription(((StringField) inputItem.getField("Product Description")).getData());


    }

}
