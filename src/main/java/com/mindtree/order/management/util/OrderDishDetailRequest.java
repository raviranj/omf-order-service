package com.mindtree.order.management.util;

import lombok.Data;

@Data
public class OrderDishDetailRequest {
	public Long dishId;
	public Long quantity;
	public double price;
}
