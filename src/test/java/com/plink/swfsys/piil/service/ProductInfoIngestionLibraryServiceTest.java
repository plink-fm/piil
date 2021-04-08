package com.plink.swfsys.piil.service;

import com.plink.swfsys.piil.ConfigProperties;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.ProductRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
//@SpringJUnitConfig(classes= ConfigProperties.class)
@SpringJUnitConfig
public class ProductInfoIngestionLibraryServiceTest {

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
    void testProcessStore_Success() {
        List<String> inputList = Arrays.stream(inputLines).collect(Collectors.toList());

        List<ProductRecord> productRecords = productInfoIngestionLibraryService.processStore(inputList);

        DefaultProductRecord productRecord = (DefaultProductRecord) productRecords.get(0);
        assertEquals(productRecord.getProductDescription(), "Kimchi-flavored white rice");
        assertEquals(productRecord.getRegularDisplayPrice(), "$5.67");
        assertEquals(productRecord.getUnitOfMeasure(), "Each");
        assertEquals(productRecord.getTaxRate(), "0.00");

        productRecord = (DefaultProductRecord) productRecords.get(1);
        assertEquals(productRecord.getProductDescription(), "Generic Soda 12-pack");
        assertEquals(productRecord.getRegularDisplayPrice(), "2 for $13.00");
        assertEquals(productRecord.getTaxRate(), "0.07775");

    }

}