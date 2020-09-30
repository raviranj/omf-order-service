package com.mindtree.order.management.util;

import java.util.List;

import lombok.Data;

@Data
public class UserOrderRequest {
	private Long userId;
	private String paymentType;
	private String instruction;
	public Long restaurantId;
	public List<OrderDishDetailRequest> orderDishDetailList;
}
