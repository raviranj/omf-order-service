package com.mindtree.order.management.util;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode
@Setter
public class OrderRequest {
	private Long userId;
	private String paymentType;
	private String instruction;
	public Long restaurantId;
	public List<OrderDishDetailRequest> orderDishDetailList;


}
