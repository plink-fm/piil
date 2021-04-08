package com.plink.swfsys.piil.chain_x.handler;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Utils {

    static public Double parseStr(String s) {
        NumberFormat cf = NumberFormat.getCurrencyInstance(Locale.US);
        Number number = null;
        try {
            number = cf.parse(s);
        }
        catch (ParseException e) {
            // TODO: don't swallow exception
            System.out.print("Error parsing String to Double: " + s);
            e.printStackTrace();
            return 0.00D;
        }
        return number.doubleValue();
    }
}
