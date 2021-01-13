package com.lastminute.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.lastminute.CommonUtilsForTest;
import com.lastminute.consts.ApiConsts;
import com.lastminute.model.ShoppingBasket;
import com.lastminute.service.ReceiptService;
import com.oeds.d3.wrapper.consts.CustomCodesEnum;

@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptControllerControllerTest {
	
	private static Logger logger = LogManager.getLogger(ReceiptControllerControllerTest.class);

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ReceiptService groupedOperationService;

	@Test
	public void testReceiptOk1()  {
		try {
			logger.info("testReceiptOk1");
			ShoppingBasket request = CommonUtilsForTest.getRequestExample1();
			
			this.mockMvc.perform(post(ApiConsts.RECEIPT_BASE_PATH+ReceiptController.GENERATE_RECEIPT).content(CommonUtilsForTest.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(CustomCodesEnum.OK.getCode())))
					.andExpect(content().string(containsString("29.83")))
					.andExpect(content().string(containsString("1.50")))
					.andExpect(content().string(containsString("16.49")));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
	}
	
	@Test
	public void testReceiptOk2()  {
		try {
			logger.info("testReceiptOk2");
			ShoppingBasket request = CommonUtilsForTest.getRequestExample2();
			
			this.mockMvc.perform(post(ApiConsts.RECEIPT_BASE_PATH+ReceiptController.GENERATE_RECEIPT).content(CommonUtilsForTest.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(CustomCodesEnum.OK.getCode())))
					.andExpect(content().string(containsString("65.15")))
					.andExpect(content().string(containsString("7.65")))
					.andExpect(content().string(containsString("54.65")))
					.andExpect(content().string(containsString("10.50")));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
	}
	
	@Test
	public void testReceiptOk3()  {
		try {
			logger.info("testReceiptOk3");
			ShoppingBasket request = CommonUtilsForTest.getRequestExample3();
			
			this.mockMvc.perform(post(ApiConsts.RECEIPT_BASE_PATH+ReceiptController.GENERATE_RECEIPT).content(CommonUtilsForTest.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isOk())
					.andExpect(content().string(containsString(CustomCodesEnum.OK.getCode())))
					.andExpect(content().string(containsString("32.19")))
					.andExpect(content().string(containsString("20.89")))
					.andExpect(content().string(containsString("9.75")))
					.andExpect(content().string(containsString("11.85")))
					.andExpect(content().string(containsString("6.70")))
					.andExpect(content().string(containsString("74.68")));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
	}
	
	@Test
	public void testReceiptKo1()  {
		try {
			logger.info("testReceiptKo1");
			ShoppingBasket request = CommonUtilsForTest.getRequestKoValidation1();
			
			this.mockMvc.perform(post(ApiConsts.RECEIPT_BASE_PATH+ReceiptController.GENERATE_RECEIPT).content(CommonUtilsForTest.asJsonString(request)).contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().isBadRequest())
					.andExpect(content().string(containsString("Validation failed for argument")));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
	}
	
	
}
