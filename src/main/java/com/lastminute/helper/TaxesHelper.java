package com.lastminute.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.lastminute.consts.TexesConsts;

public class TaxesHelper {
	
	public static BigDecimal roundToNearest5Cents(BigDecimal value) {
        return value.setScale(2, RoundingMode.CEILING)
        		.multiply(new BigDecimal("20")) //moltiplico * 20
                .setScale(0, RoundingMode.CEILING) //arrotondo a intero
                .divide(new BigDecimal("20")) //divido * 20
                .setScale(2, RoundingMode.CEILING); 
    }


	public static BigDecimal calculateImportTaxes(BigDecimal value) {
		BigDecimal percentage = getBasePercent(value);
		return roundToNearest5Cents(percentage);
    }
	
	public static BigDecimal calculateBaseTaxes(BigDecimal value) {
		BigDecimal percentage = getImportPercent(value);
		return roundToNearest5Cents(percentage);
    }
	
	public static BigDecimal getImportPercent(BigDecimal value) {
		return calculatePercentage(value, new BigDecimal(TexesConsts.IMPORT_TAXES_PERCENT));
    }
	
	public static BigDecimal getBasePercent(BigDecimal value) {
        return calculatePercentage(value, new BigDecimal(TexesConsts.BASE_TAXES_PERCENT));
    }
	
	public static BigDecimal calculatePercentage(BigDecimal value, BigDecimal percentage) {
        return value.multiply(percentage).divide(new BigDecimal(100));
    }
}
