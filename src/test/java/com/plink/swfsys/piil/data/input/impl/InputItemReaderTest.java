package com.plink.swfsys.piil.data.input.impl;

import com.plink.swfsys.piil.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputItemReaderTest {

    final String testLine1 =
            "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz";

    @Autowired
    private ConfigProperties configProperties;

    @Test
    void testReader1() {
//        InputItemReader inputItemReader = new InputItemReader(configProperties.getInputItemDescriptors());
//        inputItemReader.readItem(testLine1);

    }
}
