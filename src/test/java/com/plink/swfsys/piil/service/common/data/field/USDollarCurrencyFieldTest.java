package com.plink.swfsys.piil.service.common.data.field;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class USDollarCurrencyFieldTest {

    @Test
    void getData() {
    }

    @Test
    void parseDouble_success() {
        String result = USDollarCurrencyField.parseDouble(Double.valueOf("00000567"));
        assertEquals("$567.00", result);
    }

    @Test
    void parseUSDCurrency_success() {
        String result = USDollarCurrencyField.parse("00000567");
        assertEquals("$5.67", result);
    }

    @Test
    void parseNegativeUSDCurrency_success() {
        String result = USDollarCurrencyField.parse("-0000567");
        assertEquals("-$5.67", result);
    }

    @Test
    void parseBadInput_success() {
        assertThrows(NumberFormatException.class, () -> {
            USDollarCurrencyField.parse("foo");
        });
    }
}