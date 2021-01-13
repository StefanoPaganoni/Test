package com.lastminute.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_EMPTY)
@Builder
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor(access=AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PUBLIC)
public class BasketItem {
	
	@NotNull (message = "isImported is null")
	boolean imported;
	@NotNull (message = "category is null")
	String category;
	@NotNull (message = "itemName is null")
	String itemName;
	@NotNull (message = "itemPrice is null")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	BigDecimal  itemPrice;
	@NotNull (message = "quantity is null")
	Integer quantity;

}
