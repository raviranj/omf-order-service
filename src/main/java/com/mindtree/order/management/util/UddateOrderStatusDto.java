package com.mindtree.order.management.util;

import lombok.Data;

@Data
public class UddateOrderStatusDto {
	private Long orderId;
	private String status;
}
