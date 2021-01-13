package com.lastminute.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class ShoppingBasket {
	
	@NotNull (message = "Item list is null")
	@Valid
	List<BasketItem> items;

}
