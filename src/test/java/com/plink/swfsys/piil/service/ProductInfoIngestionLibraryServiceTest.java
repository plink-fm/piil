package com.plink.swfsys.piil.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ProductInfoIngestionLibraryServiceTest {

    private final String[] inputLines = new String[]{
            "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz",
            "14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz",
            "40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          ",
            "50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb"
    };

    @Autowired
    private ProductInfoIngestionLibraryService productInfoIngestionLibraryService;

    @Test
    void testProcess() {
        // TODO: pass in data
//        productInfoIngestionLibraryService.processStore(null);
    }

    @Test
    void testProcessStore() {
        List<String> inputList = Arrays.stream(inputLines).collect(Collectors.toList());

        productInfoIngestionLibraryService.processStore(inputList);
    }

}