package com.mindtree.order.management.util;

import java.util.List;

import lombok.Data;

@Data
public class OderRequestDto {
	private Long userId;
	private String paymentType;
	private String instruction;
	private String delivaryBoy;
	private String username;
	private List<OrderDishDetailRequest> orderDishDetailList;
	private SearchRestaurentResponse restaurantinfo;
}
