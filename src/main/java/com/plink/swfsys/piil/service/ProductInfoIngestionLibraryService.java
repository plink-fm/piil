package com.plink.swfsys.piil.service;

import com.plink.swfsys.piil.ConfigProperties;
import com.plink.swfsys.piil.data.input.InputItemDescriptor;
import com.plink.swfsys.piil.data.input.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@EnableConfigurationProperties(ConfigProperties.class)
@Component
public class ProductInfoIngestionLibraryService {

    @Autowired
    private ConfigProperties configProperties;

    private List<InputItemDescriptor> inputItemDescriptors;
    private InputItemReader inputItemReader;
    private InputItemTransformer inputItemTransformer;
    private ProductRecordWriter productRecordWriter;

    public ProductInfoIngestionLibraryService(ConfigProperties configProperties) {
        this.configProperties = configProperties;
        inputItemDescriptors = configProperties.getInputItemDescriptors();
        inputItemReader = new InputItemReader(inputItemDescriptors);
        inputItemTransformer = new InputItemTransformer(inputItemDescriptors);
        productRecordWriter = new ProductRecordWriter();
    }


    public List<ProductRecord> process() {

        // TODO: identify the store - "Each store in a grocery chain has its own product catalog service"

        // TODO: read from file
        String[] inputLines = new String[]{
                "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz",
                "14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz",
                "40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          ",
                "50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb"
        };

        List<InputItem> inputItems = new ArrayList<>();
        List<ProductRecord> productRecords = new ArrayList<>();

        for (String inputLine : inputLines) {

            InputItem inputItem = inputItemReader.readItem(inputLine);
            inputItems.add(inputItem);

            inputItemTransformer.transform(inputItem);
            ProductRecord productRecord = productRecordWriter.create(inputItem);
            productRecords.add(productRecord);
        }

        // TODO: persist ProductRecords?
        return productRecords;
    }
}
