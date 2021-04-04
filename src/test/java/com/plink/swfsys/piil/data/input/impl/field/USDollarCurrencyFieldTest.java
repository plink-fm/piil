package com.plink.swfsys.piil.data.input.impl.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class USDollarCurrencyFieldTest {

    @Test
    void getData() {
    }

    @Test
    void parseUSDCurrency() {
        // TODO: refactor parse method in USDollarCurrencyField
        String result = new USDollarCurrencyField("00000567").parse("00000567");
        assertEquals("$5.67", result);
    }

    @Test
    void parseNegativeUSDCurrency() {
        // TODO: refactor parse method in USDollarCurrencyField
        String result = new USDollarCurrencyField("-0000567").parse("-0000567");
        assertEquals("-$5.67", result);
    }
}