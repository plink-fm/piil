package com.plink.swfsys.piil.service.common.data.fixedwidth;

import com.plink.swfsys.piil.ConfigProperties;
import com.plink.swfsys.piil.service.common.data.DefaultInputSpecification;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FixedWidthStringInputItemReaderTest {

    @Autowired
    private ConfigProperties configProperties;

    FixedWidthStringInputItemReader fixedWidthStringInputItemReader = new FixedWidthStringInputItemReader();

    String[] inputLines = new String[] {
            "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz",
            "14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz",
            "40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          ",
            "50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb"
    };


    @BeforeAll
    static void setup() {
    }

    @Test
    void testReadItem_Success() {

        InputSpecification inputSpecification =
                new DefaultInputSpecification(0, 0, Double.valueOf("0.0775"), configProperties.getFixedWidthInputItemDescriptors());

        InputItem inputItem = fixedWidthStringInputItemReader.readItem(inputSpecification, inputLines[0]);
        assertEquals(80000001, inputItem.getField("Product ID").getData());
        assertEquals("Kimchi-flavored white rice                                 ",
                inputItem.getField("Product Description").getData());
        assertEquals("$5.67", inputItem.getField("Regular Each Price").getData());
        assertEquals("$0.00", inputItem.getField("Sale Each Price").getData());
        assertEquals("NNNNNNNNN", inputItem.getField("Flags").getData());
        assertEquals("     18oz", inputItem.getField("Product Size").getData());
    }

    @Test
    void testReadItem_null_input() {

        InputSpecification inputSpecification =
                new DefaultInputSpecification(0, 0, Double.valueOf("0.0775"), configProperties.getFixedWidthInputItemDescriptors());

        InputItem inputItem = fixedWidthStringInputItemReader.readItem(inputSpecification, null);
        assertNull(inputItem);
    }

    @Test
    void testReadItem_empty_string_input() {

        InputSpecification inputSpecification =
                new DefaultInputSpecification(0, 0, Double.valueOf("0.0775"), configProperties.getFixedWidthInputItemDescriptors());

        InputItem inputItem = fixedWidthStringInputItemReader.readItem(inputSpecification, "");
        assertNull(inputItem);
    }

    @Test
    void testReadItem_malformed_input() {

        InputSpecification inputSpecification =
                new DefaultInputSpecification(0, 0, Double.valueOf("0.0775"), configProperties.getFixedWidthInputItemDescriptors());

        InputItem inputItem = fixedWidthStringInputItemReader.readItem(inputSpecification, "foo bar baz");
        assertNull(inputItem);
    }
}