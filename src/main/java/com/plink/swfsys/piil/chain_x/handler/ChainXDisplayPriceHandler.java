package com.plink.swfsys.piil.chain_x.handler;

import com.plink.swfsys.piil.service.InputItemHandler;
import com.plink.swfsys.piil.service.common.data.PriceLevel;
import com.plink.swfsys.piil.service.common.data.PriceLevelDescriptor;
import com.plink.swfsys.piil.service.common.data.PriceMode;
import com.plink.swfsys.piil.service.common.data.field.IntegerField;
import com.plink.swfsys.piil.service.common.data.field.USDollarCurrencyField;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

public class ChainXDisplayPriceHandler implements InputItemHandler {

    @Override
    public void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        displayPriceInner(inputItem, PriceLevel.Regular, productRecord);
        displayPriceInner(inputItem, PriceLevel.Sale, productRecord);
    }

    protected void displayPriceInner(InputItem inputItem, PriceLevel priceLevel, ProductRecord productRecord) {
        String displayPrice;
        PriceLevelDescriptor priceLevelDescriptor;

        String splitPriceStr = ((USDollarCurrencyField) inputItem.getField(priceLevel.toString() + " Split Price")).getData();
        Double splitPrice = Utils.parseStr(splitPriceStr);

        if (splitPrice > 0) {
            priceLevelDescriptor = new PriceLevelDescriptor(PriceMode.Split, splitPrice);
        }
        else {
            String eachPriceStr = ((USDollarCurrencyField) inputItem.getField(priceLevel.toString() + " Each Price")).getData();
            Double eachPrice = Utils.parseStr(eachPriceStr);
            priceLevelDescriptor = new PriceLevelDescriptor(PriceMode.Each, eachPrice);
        }

        if (priceLevelDescriptor.getPriceMode() == PriceMode.Each) {
            displayPrice = USDollarCurrencyField.parseDouble(priceLevelDescriptor.getPrice());
        }
        else if (priceLevelDescriptor.getPriceMode() == PriceMode.Split) {
            displayPrice =
                    ((IntegerField) inputItem.getField(priceLevel.toString() + " Split Quantity")).getData().toString() +
                            " for " + USDollarCurrencyField.parseDouble(priceLevelDescriptor.getPrice());
        }
        else {
            throw new IllegalStateException("unknown price level");
        }

        if (priceLevel == PriceLevel.Regular) {
            ((DefaultProductRecord) productRecord).setRegularDisplayPrice(displayPrice);
        }
        else if (priceLevel == PriceLevel.Sale) {
            ((DefaultProductRecord) productRecord).setSaleDisplayPrice(displayPrice);
        }
        else {
            throw new IllegalStateException("unknown price level");
        }
    }
}
