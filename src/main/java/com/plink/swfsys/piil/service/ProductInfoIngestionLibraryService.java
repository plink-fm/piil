package com.plink.swfsys.piil.service;

import com.plink.swfsys.piil.ConfigProperties;
import com.plink.swfsys.piil.chain_x.data.ChainXInputSpecification;
import com.plink.swfsys.piil.service.common.DefaultProductRecordWriter;
import com.plink.swfsys.piil.chain_x.handler.*;
import com.plink.swfsys.piil.service.common.DefaultProductRecordFactory;
import com.plink.swfsys.piil.service.common.data.fixedwidth.FixedWidthStringInputItemReader;
import com.plink.swfsys.piil.service.common.DefaultProductRecordPostProcessor;
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

        // TODO: move the creation of the InputItemSpecification to a factory class
        fixedWidthInputItemDescriptors = configProperties.getFixedWidthInputItemDescriptors();

        inputSpecification = new ChainXInputSpecification(
                configProperties.getChainId(),
                configProperties.getStoreId(),
                configProperties.getTaxRate(),
                fixedWidthInputItemDescriptors,
                configProperties.getPerWeightFlagIndex(),
                configProperties.getTaxableFlagIndex());

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

        List<ProductRecord> productRecords = new ArrayList<>();

        //
        // Processing pipeline:  read input -> create ProductRecord -> run handlers -> writer -> run any post processors
        //

        for (String inLine : inData) {

            InputItem inputItem = inputItemReader.readItem(inputSpecification, inLine);
            if (inputItem == null) {
                // TODO: capture and aggregate error items
                continue;
            }

            ProductRecord productRecord = productRecordFactory.create(inputSpecification, inputItem);
            productRecords.add(productRecord);

            for (InputItemHandler handler : handlers) {
                try {
                    handler.handleItem(inputSpecification, inputItem, productRecord);
                } catch (Exception e) {
                    // TODO: capture and aggregate error items
                    System.out.println(String.format("Error handling item: %s  %s",
                            inputItem.getField("Product ID").getData(), inputItem.getField("Product ID").getData()));
                    e.printStackTrace();
                }
            }

            writer.write(inputSpecification, inputItem, productRecord);
            System.out.println(productRecord);

            for (ProductRecordPostProcessor postProcessor : postProcessors) {
                // TODO: capture and aggregate error items
                postProcessor.process(productRecord);
            }
        }

        System.out.println();
        return productRecords;
    }


}
