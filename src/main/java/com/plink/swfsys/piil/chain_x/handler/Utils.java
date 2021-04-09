package com.plink.swfsys.piil.chain_x.handler;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Utils {

    static public Double parseStr(String s) throws ParseException {
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        Number number = cf.parse(s);
        return number.doubleValue();
    }
}
