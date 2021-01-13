package com.lastminute.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lastminute.util.CommonUtils;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


	 @Override
	    protected ResponseEntity<Object> handleExceptionInternal(
	                                      Exception exception, 
	                                      Object body, 
	                                      HttpHeaders headers, 
	                                      HttpStatus status, 
	                                      WebRequest request) {
		 return new ResponseEntity<>(CommonUtils.manageSpringDefaultException(exception), headers, status);

	    }
}