package com.mindtree.order.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.order.management.service.OrderService;
import com.mindtree.order.management.util.OrderDto;
import com.mindtree.order.management.util.UddateOrderStatusDto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@OpenAPIDefinition(info = @Info(title = "OMF ORDER API", version = "v1"))
@RequestMapping("/api/omf/order")
public class OrderController {

	@Autowired
	private OrderService service;

	/**
	 * This api helps in fetching the orders by order-id
	 * 
	 * @param orderId
	 * @return OrderDto
	 */
	@GetMapping("/findByOrderId/{orderId}")
	public OrderDto findByOrderId(@PathVariable("orderId") Long orderId) {
		return service.findByOrderId(orderId);
	}

	/**
	 * This api provides functionality to user to view its latest orders
	 * 
	 * @param userId
	 * @return list of OrderDto
	 */
	@GetMapping("/findByUserId/{userId}")
	public List<OrderDto> findByUserId(@PathVariable("userId") Long userId) {
		return service.findByUserId(userId);
	}

	/*
	 * Methods commented out as this functionality get added in USER SERVICE TO
	 * IMPLEMENT KAFKA
	 * 
	 * @PostMapping("/placeOrder") public String saveOrder(@RequestBody OrderRequest
	 * orderDto) { return service.saveOrder(orderDto); }
	 */

	/**
	 * This api helps in updating the status of the order,like
	 * DELIVERD,COMPLETED,PENDING OR PENDING.
	 * 
	 * @param uddateOrderStatusDto
	 * @return String , message.
	 */
	@PostMapping("/updateOrderStatus")
	public String updateOrderStatus(@RequestBody UddateOrderStatusDto uddateOrderStatusDto) {
		if (uddateOrderStatusDto.getOrderId() != null)
			service.updateOrderStatus(uddateOrderStatusDto.getOrderId(), uddateOrderStatusDto.getStatus());
		return "Status Updated";
	}
}
