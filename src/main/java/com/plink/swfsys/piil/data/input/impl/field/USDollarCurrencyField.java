package com.plink.swfsys.piil.data.input.impl.field;

import java.util.Currency;

public class USDollarCurrencyField implements CurrencyField<Currency> {

    private String data;

    //private boolean isNegative = false;

    private Currency currency;


    public USDollarCurrencyField(String data) {
        this.data = parse(data);
        //isNegative = data.startsWith("-");
    }

    @Override
    public Currency getData() {
        return currency;
    }

//    public boolean isNegative() {
//        return isNegative;
//    }

    protected static String parse(String data) {

        String inData = data;
        boolean isNegative = data.startsWith("-");
        if (isNegative) {
            inData = data.substring(1, data.length());
        }

//        String substring = inData.substring(0, inData.length() - 2);
//        Object o = substring;

        double value = Double.valueOf(inData.substring(0, inData.length() - 2) + "." + inData.substring(inData.length() - 2));
        if (isNegative) {
            value *= -1.0;
        }
        java.util.Currency usd = java.util.Currency.getInstance("USD");
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        format.setCurrency(usd);
//        System.out.println(format.format(value) + " " + usd.getCurrencyCode());
        System.out.println(format.format(value));
        return format.format(value);
    }
}
