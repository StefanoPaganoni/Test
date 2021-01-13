package com.lastminute.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lastminute.configuration.EnvironmentConfigurationHolder;
import com.lastminute.exception.ReceiptException;
import com.lastminute.exception.ValidationException;
import com.lastminute.helper.TaxesHelper;
import com.lastminute.model.BaseResponse;
import com.lastminute.model.BasketItem;
import com.lastminute.model.Receipt;
import com.lastminute.model.ShoppingBasket;
import com.lastminute.util.CommonUtils;

@Service
public class ReceiptService {
	
	@Autowired
	EnvironmentConfigurationHolder envHolder;
	

	private static Logger logger = LogManager.getLogger(ReceiptService.class);


	public BaseResponse buildReceipt(ShoppingBasket shoppingBasket, HttpServletResponse response) throws ReceiptException
	{
		
		try {
			Receipt receipt = new Receipt();
			shoppingBasket.getItems().forEach(item -> buildSingleItem(item, receipt));
			logger.info("Response: "+receipt);
			return CommonUtils.manageOkResponse(response, receipt);
		} catch (Exception e) {
			throw new ReceiptException("Error in generating receipt: "+e);
		}
		
	}

	private void buildSingleItem(BasketItem item, Receipt receipt) {
		logger.info("Item: "+item);
		logger.info("-------------------");
		
		BigDecimal itemPrice = item.getItemPrice();
		BigDecimal totalPrice = receipt.getTotalPrice() == null ? new BigDecimal(0) : receipt.getTotalPrice();
		BigDecimal totalTaxes = receipt.getSaleTaxes() == null ? new BigDecimal(0) : receipt.getSaleTaxes(); 
		BasketItem receiptItem = item;
		
		if (item.isImported())
		{
			logger.info("Item imported: "+item.isImported());
			BigDecimal importTaxes = TaxesHelper.calculateImportTaxes(item.getItemPrice());
			logger.info("importTaxes: "+importTaxes);
			itemPrice = itemPrice.add(importTaxes);
			totalTaxes = totalTaxes.add(importTaxes);
			logger.info("totalTaxes: "+totalTaxes);
		}
		if (isItemBasicTaxed(item))
		{
			logger.info("Item basic taxes: "+isItemBasicTaxed(item));
			BigDecimal basicTaxes = TaxesHelper.calculateBaseTaxes(item.getItemPrice());
			logger.info("basicTaxes: "+basicTaxes);
			itemPrice = itemPrice.add(basicTaxes);
			totalTaxes = totalTaxes.add(basicTaxes);
			logger.info("totalTaxes: "+totalTaxes);
		}		
		
		logger.info("itemPrice: "+totalPrice);
		totalPrice = totalPrice.add(itemPrice);
		receiptItem.setItemPrice(itemPrice);
		receipt.setSaleTaxes(totalTaxes);
		receipt.setTotalPrice(totalPrice);
		addItemToReceipt(receipt, receiptItem);
		
	}

	private void addItemToReceipt(Receipt receipt, BasketItem receiptItem)
	{
		if (receipt.getBasket() == null)
		{
			ShoppingBasket basket = new ShoppingBasket();
			List<BasketItem> items = new ArrayList<BasketItem>();
			items.add(receiptItem);
			basket.setItems(items);
			receipt.setBasket(basket);
		}
		else
		{
			receipt.getBasket().getItems().add(receiptItem);
		}
	}
	
	private boolean isItemBasicTaxed(BasketItem item)
	{
		if (!Arrays.asList(envHolder.getExempt()).contains(item.getCategory()))
		{
			return true;
		}
		return false;
	}



}
