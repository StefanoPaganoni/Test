package com.lastminute.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lastminute.model.BaseRequest;
import com.lastminute.model.BaseResponse;
import com.lastminute.model.Receipt;
import com.oeds.d3.wrapper.consts.CustomCodesEnum;

public class CommonUtils {
	
	private static Logger logger = LogManager.getLogger(CommonUtils.class);
	
	public static String standardDatePattern = "dd/MM/yyyy";
	public static String d3DatePattern = "dd.MM.yyyy";
	
	public static void printRequest (BaseRequest request) throws JsonProcessingException
	{
		String requestAsString = Mapper.getObjectMapper().writeValueAsString(request);
		logger.info("Received request "+request.getClass().getName());
		logger.info(requestAsString);
	}
	
	public static void printResponse(BaseResponse response) throws JsonProcessingException
	{
		String responseAsString = Mapper.getObjectMapper().writeValueAsString(response);
		logger.info("Given response "+response.getClass().getName());
		logger.info(responseAsString);
	}
	
	public static BaseResponse manageOkResponse(HttpServletResponse httpResponse, Receipt receipt)
	{
		httpResponse.setStatus(HttpStatus.OK.value());
		BaseResponse response = BaseResponse.builder().responseCode(CustomCodesEnum.OK.getCode())
				.responseMessage(receipt).build();
		try {
			printResponse(response);
		} catch (JsonProcessingException e1) {
			logger.error("Error in printing response.");
		}
		return response;
	}
	
	
	public static BaseResponse manage500Exceptions(Exception e, HttpServletResponse response)
	{
		logger.error(e.getMessage(), e);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		BaseResponse errorResponse = BaseResponse.builder().responseCode(CustomCodesEnum.GENERIC_ERROR.getCode())
				.responseMessage(e.getMessage()).build();
		try {
			printResponse(errorResponse);
		} catch (JsonProcessingException e1) {
			logger.error("Error in printing response.");
		}
		return errorResponse;
	}
	
	public static BaseResponse manage400Exceptions(Exception e, HttpServletResponse response)
	{
		logger.error(e.getMessage(), e);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		BaseResponse errorResponse = BaseResponse.builder().responseCode(CustomCodesEnum.CUSTOM_VALIDATOR_EXCEPTION.getCode())
		.responseMessage(e.getMessage()).build();
		try {
			printResponse(errorResponse);
		} catch (JsonProcessingException e1) {
			logger.error("Error in printing response.");
		}
		return errorResponse;
	}


	public static Object manageSpringDefaultException(Exception e) {
		logger.error(e.getMessage(), e);
		BaseResponse errorResponse = BaseResponse.builder().responseCode(CustomCodesEnum.DEFAULT_EXCEPTION.getCode())
		.responseMessage(e.getMessage()).build();
		try {
			printResponse(errorResponse);
		} catch (JsonProcessingException e1) {
			logger.error("Error in printing response.");
		}
		return errorResponse;
	}

}
