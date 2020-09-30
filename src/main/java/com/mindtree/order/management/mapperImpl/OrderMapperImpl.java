package com.mindtree.order.management.mapperImpl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.mindtree.order.management.entity.Order;
import com.mindtree.order.management.mapper.OrderMapper;
import com.mindtree.order.management.util.OrderDto;
import com.mindtree.order.management.util.OrderRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderMapperImpl implements OrderMapper {

	DozerBeanMapper mapper = new DozerBeanMapper();

	@Override
	public Order mapToOrder(OrderRequest orderDto) {

		return mapper.map(orderDto, Order.class);
	}

	@Override
	public OrderDto mapToOrderDto(Order order) {
		OrderDto response = null;
		if (order != null) {
			response = new OrderDto();
			response.setOrderId(order.getOrderId());
			response.setUserId(order.getUserId());
			response.setCreationTime(order.getCreationTime());
			response.setDeliveryPerson(order.getDeliveryPerson());
			response.setTax(order.getTax());
			response.setOrderDishDetailList(order.getOrderDishDetailList());
			response.setEstimatedTimeInMinutes(order.getEstimatedTimeInMinutes());
			response.setNote(order.getNote());
			response.setOfferInPercentage(order.getOfferInPercentage());
			response.setOrderStatus(order.getOrderStatus());
			response.setPaymentType(order.getPaymentType());
			response.setRestaurantId(order.getRestaurantId());
			response.setRestaurantName(order.getRestaurantName());
			response.setTotalCost(order.getTotalCost());
		}

		// return mapper.map(order, OrderDto.class);
		return response;

	}

	@Override
	public List<OrderDto> mapToUserDtoList(List<Order> orderList) {
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		log.info("Oder orderd " + orderDtoList.toString());
		for (Order order : orderList) {

			OrderDto response = new OrderDto();
			response.setOrderId(order.getOrderId());
			response.setUserId(order.getUserId());
			response.setCreationTime(order.getCreationTime());
			response.setDeliveryPerson(order.getDeliveryPerson());
			if (order.getTax() > 0) {
				response.setTax(order.getTax());
			}
			response.setOrderDishDetailList(order.getOrderDishDetailList());
			response.setEstimatedTimeInMinutes(order.getEstimatedTimeInMinutes());
			response.setNote(order.getNote());
			response.setOfferInPercentage(order.getOfferInPercentage());
			response.setOrderStatus(order.getOrderStatus());
			response.setPaymentType(order.getPaymentType());
			response.setRestaurantId(order.getRestaurantId());
			response.setRestaurantName(order.getRestaurantName());
			response.setTotalCost(order.getTotalCost());
			orderDtoList.add(response);
		}
		return orderDtoList;
	}
}
