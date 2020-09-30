package com.mindtree.order.management.mapper;

import java.util.List;

import com.mindtree.order.management.entity.Order;
import com.mindtree.order.management.util.OrderDto;
import com.mindtree.order.management.util.OrderRequest;

public interface OrderMapper {

	public Order mapToOrder(OrderRequest orderDto);

	public OrderDto mapToOrderDto(Order order);

	public List<OrderDto> mapToUserDtoList(List<Order> orderList);
}
