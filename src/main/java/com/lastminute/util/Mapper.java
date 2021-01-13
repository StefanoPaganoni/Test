package com.lastminute.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
	
	static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	
	public static ObjectMapper getObjectMapper()
	{
		return mapper;
		
	}
	

}
