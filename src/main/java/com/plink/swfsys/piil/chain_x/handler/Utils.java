package com.plink.swfsys.piil.chain_x.handler;

import java.text.NumberFormat;
import java.text.ParseException;

public class Utils {

    static public Double parseStr(String s) {
        NumberFormat cf = NumberFormat.getCurrencyInstance();
        Number number = null;
        try {
            number = cf.parse(s);
        }
        catch (ParseException e) {
            // TODO: don't swallow exception
            System.out.print(e);
        }
        return number.doubleValue();
    }
}
