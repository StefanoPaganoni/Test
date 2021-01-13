package com.lastminute.controller.helper;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.lastminute.helper.TaxesHelper;

public class TaxesHelperTest {
	
	private static Logger logger = LogManager.getLogger(TaxesHelperTest.class);


	@Test
	public void testRoundUp1() {
		logger.info("testRoundUp1");
		BigDecimal result = TaxesHelper.roundToNearest5Cents(new BigDecimal("2.375"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("2.40")));
	}
	
	@Test
	public void testRoundUp2() {
		logger.info("testRoundUp2");
		BigDecimal result = TaxesHelper.roundToNearest5Cents(new BigDecimal("7.5"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("7.50")));
	}
	
	@Test
	public void testRoundUp3() {
		logger.info("testRoundUp2");
		BigDecimal result = TaxesHelper.roundToNearest5Cents(new BigDecimal("1.49"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("1.50")));
	}
	
	@Test
	public void testRoundUp4() {
		logger.info("testRoundUp4");
		BigDecimal result = TaxesHelper.roundToNearest5Cents(new BigDecimal("2.115"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("2.15")));
	}
	
	@Test
	public void testRoundUp5() {
		logger.info("testRoundUp5");
		BigDecimal result = TaxesHelper.roundToNearest5Cents(new BigDecimal("5.1"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("5.10")));
	}
	
	@Test
	public void testPercentage10() {
		logger.info("testPercentage10");
		BigDecimal result = TaxesHelper.getImportPercent(new BigDecimal("47.50"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("4.75")));
	}
	
	@Test
	public void testPercentage5() {
		logger.info("testPercentage5");
		BigDecimal result = TaxesHelper.getBasePercent(new BigDecimal("11.25"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("0.5625")));
	}

	@Test
	public void testImportTaxes() {
		logger.info("testImportTaxes");
		BigDecimal result = TaxesHelper.calculateImportTaxes(new BigDecimal("47.50"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("2.40")));
	}
	
	@Test
	public void testImportTaxes2() {
		logger.info("testImportTaxes2");
		BigDecimal result = TaxesHelper.calculateImportTaxes(new BigDecimal("11.25"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("0.60")));
	}
	
	
	@Test
	public void testBaseTaxes() {
		logger.info("testBaseTaxes");
		BigDecimal result = TaxesHelper.calculateBaseTaxes(new BigDecimal("47.50"));
		logger.info(result);

		assertTrue(result.equals(new BigDecimal("4.75")));
	}


}
