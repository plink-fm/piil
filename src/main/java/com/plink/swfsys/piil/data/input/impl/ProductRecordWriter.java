package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.data.input.impl.field.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
        boolean isPerWeight = isPerWeight(inputItem);
        Object breakPoint = isTaxable;


        // ---------------------------------------------------------------------------------
        String regularDisplayPrice = "";
        RegularPriceLevel regularPriceLevel;
        Double regularSplitPrice = ((USDollarCurrencyField) inputItem.getField("Regular Split Price")).getData();
        if (regularSplitPrice > 0) {
            regularPriceLevel = new RegularPriceLevel(PriceMode.Split, regularSplitPrice);
        }
        else {
            regularPriceLevel = new RegularPriceLevel(PriceMode.Each, ((USDollarCurrencyField) inputItem.getField("Regular Each Price")).getData());
        }

        if (regularPriceLevel.getPriceMode() == PriceMode.Each) {
            regularDisplayPrice = USDollarCurrencyField.parseDouble(regularPriceLevel.getPrice());
        }
        else if (regularPriceLevel.getPriceMode() == PriceMode.Split) {
            regularDisplayPrice =
                    ((IntegerField) inputItem.getField("Regular Split Quantity")).getData().toString() +
                    " for " + USDollarCurrencyField.parseDouble(regularPriceLevel.getPrice());
        }
        else {
            throw new IllegalStateException("unknown price level");
        }

        productRecord.setRegularDisplayPrice(regularDisplayPrice);
        // ---------------------------------------------------------------------------------

        // TODO: calculated

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        Double regularCalculatorPrice = 0.00;
        Integer regularSplitQuantity = ((IntegerField) inputItem.getField("Regular Split Quantity")).getData();
        if (regularSplitQuantity > 0) {
            regularCalculatorPrice = regularSplitPrice / regularSplitQuantity;



            Double d = regularCalculatorPrice.doubleValue();
            System.out.println(df.format(d));
            productRecord.setRegularCalculatorPrice(df.format(d));
        }
        else {
            productRecord.setRegularCalculatorPrice(df.format(regularCalculatorPrice));
        }

        // ---------------------------------------------------------------------------------

        String saleDisplayPrice = "";
        SalePriceLevel salePriceLevel;
        Double saleSplitPrice = ((USDollarCurrencyField) inputItem.getField("Sale Split Price")).getData();
        if (saleSplitPrice > 0) {
            salePriceLevel = new SalePriceLevel(PriceMode.Split, saleSplitPrice);
        }
        else {
            salePriceLevel = new SalePriceLevel(PriceMode.Each, ((USDollarCurrencyField) inputItem.getField("Sale Each Price")).getData());
        }

        if (salePriceLevel.getPriceMode() == PriceMode.Each) {
            saleDisplayPrice = USDollarCurrencyField.parseDouble(salePriceLevel.getPrice());
        }
        else if (salePriceLevel.getPriceMode() == PriceMode.Split) {
            saleDisplayPrice =
                    ((IntegerField) inputItem.getField("Sale Split Quantity")).getData().toString() +
                            " for " + USDollarCurrencyField.parseDouble(salePriceLevel.getPrice());
        }
        else {
            throw new IllegalStateException("unknown price level");
        }

        productRecord.setSaleDisplayPrice(saleDisplayPrice);
        // ---------------------------------------------------------------------------------

        // TODO: calculated

        Double saleCalculatorPrice = 0.00;
        Integer saleSplitQuantity = ((IntegerField) inputItem.getField("Sale Split Quantity")).getData();
        if (saleSplitQuantity > 0) {
            saleCalculatorPrice = saleSplitPrice / saleSplitQuantity;

            Double d = saleCalculatorPrice.doubleValue();
            System.out.println(df.format(d));
            productRecord.setSaleCalculatorPrice(df.format(d));
        }
        else {
            productRecord.setSaleCalculatorPrice(df.format(saleCalculatorPrice));
        }

        // ---------------------------------------------------------------------------------

        productRecord.setUnitOfMeasure(isPerWeight ? UnitOfMeasure.Pound : UnitOfMeasure.Each);

        // TODO: calculated double:

        if (isTaxable) {
            productRecord.setTaxRate(inputItem.getTaxRate());
        }
        else {
            productRecord.setTaxRate("0.0");
        }

        // TODO: set metadata properties on ProductRecord, e.g. createDate, updateDate, etc.

        return productRecord;
    }

    // TODO: move to PriceLevelFactory class
//    private PriceLevelBase createPriceLevel(InputItem inputItem, String fieldKey, PriceLevel priceLevel) {
//        RegularPriceLevel regularPriceLevel;
//        Double regularSplitPrice = ((USDollarCurrencyField) inputItem.getField("Regular Split Price")).getData();
//        if (regularSplitPrice > 0) {
//            regularPriceLevel = new RegularPriceLevel(PriceMode.Split, regularSplitPrice);
//        }
//        else {
//            regularPriceLevel = new RegularPriceLevel(PriceMode.Each, ((USDollarCurrencyField) inputItem.getField("Regular Each Price")).getData());
//        }
//    }

    // TODO: This needs to move to another class:
//    private PriceMode getSplitPrice(InputItem inputItem) {
//
//    }

    // TODO: This needs to move to another class:
    private boolean isTaxable(InputItem inputItem) {
        final String flags = ((FlagsField) inputItem.getField("Flags")).getData();
        final String flag = String.valueOf(flags.charAt(5 - 1));
        return flag.equals("Y");
    }

    // TODO: This needs to move to another class:
    private boolean isPerWeight(InputItem inputItem) {
        final String flags = ((FlagsField) inputItem.getField("Flags")).getData();
        final String flag = String.valueOf(flags.charAt(3 - 1));
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
