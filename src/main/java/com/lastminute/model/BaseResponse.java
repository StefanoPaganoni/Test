package com.lastminute.model;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(Include.NON_EMPTY)
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PUBLIC)
public class BaseResponse<T> {
	
	@JsonIgnore
	private HttpHeaders headers;
	
	@NotNull(message = "responseCode is null")
	String responseCode;
	@NotNull(message = "responseMessage is null")
	T responseMessage;

}
