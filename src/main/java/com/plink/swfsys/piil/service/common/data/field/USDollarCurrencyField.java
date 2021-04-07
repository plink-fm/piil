package com.plink.swfsys.piil.service.common.data.field;

import java.util.Currency;

public class USDollarCurrencyField implements CurrencyField<Currency> {

    // TODO: clean up this class

    private String data;
//    private Double dataNumber;

    public USDollarCurrencyField(String data) {
        this.data = parse(data);
    }


    @Override
    public void setData(Object data) {
        // TODO: can something be done about this cast?
        this.data = parseDouble((Double) data);
    }

//    @Override
//    public Double getData() {
//        return dataNumber;
//    }

    @Override
    public String getData() {
        return data;
    }


    protected String parse(String data) {

        String inData = data;
        boolean isNegative = data.startsWith("-");
        if (isNegative) {
            inData = data.substring(1);
        }

//        String substring = inData.substring(0, inData.length() - 2);
//        Object o = substring;

        double value = Double.valueOf(inData.substring(0, inData.length() - 2) + "." + inData.substring(inData.length() - 2));
        if (isNegative) {
            value *= -1.0;
        }

        //dataNumber = value;

        java.util.Currency usd = java.util.Currency.getInstance("USD");
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        format.setCurrency(usd);

//        System.out.println(format.format(value));
        return format.format(value);
    }

    public static String parseDouble(Double value) {

        java.util.Currency usd = java.util.Currency.getInstance("USD");
        java.text.NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        format.setCurrency(usd);

//        System.out.println(format.format(value));
        return format.format(value);
    }
}
