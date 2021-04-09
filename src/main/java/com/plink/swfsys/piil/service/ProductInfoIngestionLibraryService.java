package com.plink.swfsys.piil.service;

import com.plink.swfsys.piil.ConfigProperties;
import com.plink.swfsys.piil.service.common.DefaultProductRecordWriter;
import com.plink.swfsys.piil.chain_x.handler.*;
import com.plink.swfsys.piil.service.common.DefaultProductRecordFactory;
import com.plink.swfsys.piil.service.common.data.fixedwidth.FixedWidthStringInputItemReader;
import com.plink.swfsys.piil.service.common.DefaultProductRecordPostProcessor;
import com.plink.swfsys.piil.service.common.data.DefaultInputSpecification;
import com.plink.swfsys.piil.service.common.data.fixedwidth.DefaultFixedWidthInputItemDescriptor;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductInfoIngestionLibraryService {

    @Autowired
    private ConfigProperties configProperties;

    private List<DefaultFixedWidthInputItemDescriptor> fixedWidthInputItemDescriptors;

    private InputSpecification inputSpecification;

    public ProductInfoIngestionLibraryService(ConfigProperties configProperties) {
        this.configProperties = configProperties;

        // TODO: read in global settings from properties file, like storeId and taxRate
        // TODO: create an InputItemSpecification that wraps the descriptors and globals?

        // TODO: move the creation of the InputItemSpecification outside this class?
        fixedWidthInputItemDescriptors = configProperties.getFixedWidthInputItemDescriptors();

        inputSpecification = new DefaultInputSpecification(
                configProperties.getChainId(),
                configProperties.getStoreId(),
                configProperties.getTaxRate(),
                fixedWidthInputItemDescriptors);

        writer = new DefaultProductRecordWriter();

        handlers = new ArrayList<>();
        handlers.add(new ChainXStringHandler());
        handlers.add(new ChainXDisplayPriceHandler());
        handlers.add(new ChainXCalculatorHandler());
        handlers.add(new ChainXUnitOfMeasureHandler());
        handlers.add(new ChainXTaxHandler());

        postProcessors = new ArrayList<>();
        postProcessors.add(new DefaultProductRecordPostProcessor());
    }


    private InputItemReader inputItemReader2 = new FixedWidthStringInputItemReader();
    private ProductRecordFactory defaultProductRecordFactory = new DefaultProductRecordFactory();
    private List<InputItemHandler> handlers;
    private ProductRecordWriter writer;
    private List<ProductRecordPostProcessor> postProcessors;

    public List<ProductRecord> processStore(List<String> inData) {

        return processStore(inputSpecification,
                inData,
                inputItemReader2,
                defaultProductRecordFactory,
                handlers,
                writer,
                postProcessors);
    }

    public List<ProductRecord> processStore(InputSpecification inputSpecification,
                                            List<String> inData) {

        return processStore(inputSpecification,
                inData,
                inputItemReader2,
                defaultProductRecordFactory,
                handlers,
                writer,
                postProcessors);
    }

    public List<ProductRecord> processStore(InputSpecification inputSpecification,
                                            List<String> inData,
                                            InputItemReader inputItemReader,
                                            ProductRecordFactory productRecordFactory,
                                            List<InputItemHandler> handlers,
                                            ProductRecordWriter writer,
                                            List<ProductRecordPostProcessor> postProcessors) {

        System.out.println();
//
//        // TODO: read from file
//        String[] inputLines = new String[]{
//                "80000001 Kimchi-flavored white rice                                  00000567 00000000 00000000 00000000 00000000 00000000 NNNNNNNNN      18oz",
//                "14963801 Generic Soda 12-pack                                        00000000 00000549 00001300 00000000 00000002 00000000 NNNNYNNNN   12x12oz",
//                "40123401 Marlboro Cigarettes                                         00001000 00000549 00000000 00000000 00000000 00000000 YNNNNNNNN          ",
//                "50133333 Fuji Apples (Organic)                                       00000349 00000000 00000000 00000000 00000000 00000000 NNYNNNNNN        lb"
//        };
//

        List<ProductRecord> productRecords = new ArrayList<>();

        //
        // Processing pipeline:  read input -> create ProductRecord -> run handlers -> writer -> run any post processors
        //

        for (String inLine : inData) {

            InputItem inputItem = inputItemReader.readItem(inputSpecification, inLine);
            if (inputItem == null) {
                continue;
            }

            ProductRecord productRecord = productRecordFactory.create(inputSpecification, inputItem);
            productRecords.add(productRecord);

            for (InputItemHandler handler : handlers) {
                handler.handleItem(inputSpecification, inputItem, productRecord);
            }

            writer.write(inputSpecification, inputItem, productRecord);
            System.out.println(productRecord);

            for (ProductRecordPostProcessor postProcessor : postProcessors) {
                postProcessor.process(productRecord);
            }
        }

        System.out.println();
        return productRecords;
    }


}
