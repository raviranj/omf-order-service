package com.mindtree.order.management.service;

import java.util.List;

import com.mindtree.order.management.util.OrderDto;

public interface OrderService {

	public OrderDto findByOrderId(Long orderId);

	public List<OrderDto> findByUserId(Long userId);

	public String saveOrder(String orderDto);

	public void updateOrderStatus(Long orderId, String status);
}
