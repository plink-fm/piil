package com.plink.swfsys.piil.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductInfoIngestionLibraryServiceTest {

    @Autowired
    private ProductInfoIngestionLibraryService productInfoIngestionLibraryService;

    @Test
    void testProcess() {
        productInfoIngestionLibraryService.process();
    }

}