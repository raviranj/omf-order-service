package com.mindtree.order.management.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.mindtree.order.management.entity.OrderDishDetail;

import lombok.Data;

@Data
public class OrderDto {

	private Long orderId;
	private Long userId;
	private String paymentType;
	private OrderStatus orderStatus;
	private double totalCost;
	private String note;
	private int estimatedTimeInMinutes;
	private int offerInPercentage;
	private Long restaurantId;
	private String restaurantName;
	private String deliveryPerson;
	private double tax;
	private List<OrderDishDetail> orderDishDetailList;
	private LocalDateTime creationTime;
	private Date lastModifyTime;
}
