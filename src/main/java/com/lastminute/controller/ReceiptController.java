package com.lastminute.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lastminute.consts.ApiConsts;
import com.lastminute.exception.ReceiptException;
import com.lastminute.model.BaseResponse;
import com.lastminute.model.ShoppingBasket;
import com.lastminute.service.ReceiptService;
import com.lastminute.util.CommonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "LastMinute Api" })
@Controller
@RequestMapping(ApiConsts.RECEIPT_BASE_PATH)
public class ReceiptController {

		private static Logger logger = LogManager.getLogger(ReceiptController.class);

		public static final String GENERATE_RECEIPT = "/generate";

		@Autowired
		ReceiptService receiptService;

		@ApiOperation(value = "Generate receipt", notes = "Allows you to generate receipt")
		@ApiResponses({
				@ApiResponse(code = ApiConsts.SUCCESS_STATUS, message = ApiConsts.SUCCESS_MESSAGE, response = BaseResponse.class),
				@ApiResponse(code = ApiConsts.BAD_REQUEST_STATUS, message = ApiConsts.BAD_REQUEST_MESSAGE, response = BaseResponse.class),
				@ApiResponse(code = ApiConsts.INTERNAL_ERROR_STATUS, message = ApiConsts.INTERNAL_ERROR_MESSAGE, response = BaseResponse.class) })
		@RequestMapping(value = GENERATE_RECEIPT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody BaseResponse generateReceipt(HttpServletRequest request,
				@RequestBody @Valid ShoppingBasket receiptRequest, HttpServletResponse response) {
			logger.info(String.format("%s --> %s %s", "ReceiptController", GENERATE_RECEIPT,
					":::::::::::::::::::::::::::::"));
			try {
				
				logger.info("Received request: "+receiptRequest);
				return receiptService.buildReceipt(receiptRequest, response);

			} catch (ReceiptException e) {
				return CommonUtils.manage500Exceptions(e, response);
			} 

		}

		

}
