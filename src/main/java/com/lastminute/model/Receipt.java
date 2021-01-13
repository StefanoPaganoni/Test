package com.lastminute.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PUBLIC)
public class Receipt extends BaseResponse{
	
	ShoppingBasket basket;
	BigDecimal  saleTaxes;
	BigDecimal  totalPrice;
	

}
