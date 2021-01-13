package com.oeds.d3.wrapper.consts;

public enum CustomCodesEnum {
	
	OK("OK","Response received correctly"),
	CUSTOM_VALIDATOR_EXCEPTION("CUSTOM_VALIDATOR_ERROR","Validation exception."),
	DEFAULT_EXCEPTION ("DEFAULT_EXCEPTION","Spring default exception"),
	GENERIC_ERROR ("GENERIC_ERROR","Generic error. Please contact the administrator");

	
	private String code;
	private String message;
	
	private CustomCodesEnum(String code, String message) {
		this.code=code;
		this.message=message;
	}
	

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
