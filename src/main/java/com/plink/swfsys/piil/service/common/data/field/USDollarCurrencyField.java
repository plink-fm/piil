package com.plink.swfsys.piil.service.common.data.field;

import java.text.NumberFormat;
import java.util.Currency;

public class USDollarCurrencyField implements CurrencyField<Currency> {

    // TODO: clean up this class

    private String data;

    public USDollarCurrencyField(String data) {
        this.data = parse(data);
    }

    @Override
    public void setData(Object data) {
        this.data = parseDouble((Double) data);
    }

    @Override
    public String getData() {
        return data;
    }


    /**
     * Parses a string into a USD string. Assumes a format of last 2 digits are cents (without '.' delimiter).
     *
     * @param data
     * @return the USD string
     */
    protected static String parse(String data) {

        String inData = data;
        boolean isNegative = data.startsWith("-");
        if (isNegative) {
            inData = data.substring(1);
        }

        double value = Double.valueOf(inData.substring(0, inData.length() - 2) + "." + inData.substring(inData.length() - 2));
        if (isNegative) {
            value *= -1.0;
        }

        return parseDouble(value);
    }

    /**
     * Parses a raw double into a USD string.
     *
     * @param value
     * @return the USD string
     */
    public static String parseDouble(Double value) {
        // TODO: externalize Currency to account for other locales
        Currency usd = java.util.Currency.getInstance("USD");
        NumberFormat format = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        format.setCurrency(usd);

        return format.format(value);
    }
}
