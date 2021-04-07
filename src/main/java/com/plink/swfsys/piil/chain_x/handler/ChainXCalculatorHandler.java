package com.plink.swfsys.piil.chain_x.handler;

import com.plink.swfsys.piil.service.InputItemHandler;
import com.plink.swfsys.piil.service.common.data.PriceLevel;
import com.plink.swfsys.piil.service.common.data.field.IntegerField;
import com.plink.swfsys.piil.service.common.data.field.USDollarCurrencyField;
import com.plink.swfsys.piil.service.common.data.DefaultProductRecord;
import com.plink.swfsys.piil.service.data.InputItem;
import com.plink.swfsys.piil.service.data.InputSpecification;
import com.plink.swfsys.piil.service.data.ProductRecord;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ChainXCalculatorHandler implements InputItemHandler {

    @Override
    public void handleItem(InputSpecification inputSpecification, InputItem inputItem, ProductRecord productRecord) {
        calculatorPriceInner(inputItem, PriceLevel.Regular, productRecord);
        calculatorPriceInner(inputItem, PriceLevel.Sale, productRecord);
    }

    protected void calculatorPriceInner(InputItem inputItem, PriceLevel priceLevel, ProductRecord productRecord) {
        String splitPriceStr = ((USDollarCurrencyField) inputItem.getField(priceLevel.toString() + " Split Price")).getData();
        Double splitPrice = Utils.parseStr(splitPriceStr);

        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.HALF_DOWN);

        String calculatorPriceStr;

        double calculatorPrice = 0.00;
        Integer splitQuantity = ((IntegerField) inputItem.getField(priceLevel.toString() + " Split Quantity")).getData();
        if (splitQuantity > 0) {
            calculatorPrice = splitPrice / splitQuantity;

            Double d = calculatorPrice;
            calculatorPriceStr = df.format(d);
        }
        else {
            calculatorPriceStr = df.format(calculatorPrice);
        }

        if (priceLevel == PriceLevel.Regular) {
            ((DefaultProductRecord) productRecord).setRegularCalculatorPrice(calculatorPriceStr);
        }
        else if (priceLevel == PriceLevel.Sale) {
            ((DefaultProductRecord) productRecord).setSaleCalculatorPrice(calculatorPriceStr);
        }
        else {
            throw new IllegalStateException("unknown price level");
        }
    }
}
