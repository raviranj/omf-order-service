package com.mindtree.order.management.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.order.management.dao.OrderDao;
import com.mindtree.order.management.entity.Order;
import com.mindtree.order.management.entity.OrderDishDetail;
import com.mindtree.order.management.mapper.OrderMapper;
import com.mindtree.order.management.service.OrderService;
import com.mindtree.order.management.util.OderRequestDto;
import com.mindtree.order.management.util.OrderDishDetailRequest;
import com.mindtree.order.management.util.OrderDto;
import com.mindtree.order.management.util.OrderRequest;
import com.mindtree.order.management.util.OrderStatus;
import com.mindtree.order.management.util.SearchRestaurentResponse;

import lombok.extern.slf4j.Slf4j;;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper mapper;

	/*
	 * @Autowired private UserServiceProxy uearchServiceProxy;
	 * 
	 * @Autowired private SearchServiceProxy searchServiceProxy;
	 */

	List<String> deliverboy = Arrays.asList("ALEX", "BOB", "FINCH", "ROSEY");

	@Autowired
	private OrderDao dao;

	@Override
	public OrderDto findByOrderId(Long orderId) {
		Optional<Order> order = dao.findById(orderId);
		return mapper.mapToOrderDto(order.get());
	}

	@Override
	public List<OrderDto> findByUserId(Long userId) {
		List<Order> orderList = dao.findByUserId(userId);
		return mapper.mapToUserDtoList(orderList);
	}

	@Override
	@KafkaListener(topics = "saveOrder", groupId = "group_id")
	public String saveOrder(String obj) {
		log.info("inside save Order method of OrderService  " + obj.toString());
		ObjectMapper ordevalue = new ObjectMapper();
		OderRequestDto orderRequest = null;
		double sum = 0;
		int min = 20;
		int max = 50;
		double discountprice = 0;
		double taxprice = 0;
		Order order = null;
		try {
			orderRequest = ordevalue.readValue(obj, OderRequestDto.class);
			order = new Order();
			SearchRestaurentResponse restaurantinfo = orderRequest.getRestaurantinfo();
			OrderDishDetail orderDishDetail = new OrderDishDetail();
			List<OrderDishDetail> orderDishDetailList = new ArrayList<>();
			List<OrderDishDetailRequest> orderlist = orderRequest.getOrderDishDetailList();
			for (OrderDishDetailRequest dish : orderlist) {
				sum += (dish.getPrice() * dish.getQuantity());
				orderDishDetail.setDishId(dish.getDishId());
				orderDishDetail.setOrder(order);
				orderDishDetail.setPrice(dish.getPrice());
				orderDishDetail.setQuantity(dish.getQuantity());
				orderDishDetailList.add(orderDishDetail);
			}
			discountprice = sum - (sum * Integer.valueOf(restaurantinfo.getRestaurantOffer()) / 100);
			taxprice = discountprice + ((discountprice * restaurantinfo.getTax()) / 100);
			order.setTotalCost(taxprice);
			order.setRestaurantName(restaurantinfo.getRestaurantName());
			int random_int = (int) (Math.random() * (max - min + 1) + min);
			Random ran = new Random();
			String deliveryperson = deliverboy.get(ran.nextInt(deliverboy.size()));
			order.setDeliveryPerson(deliveryperson);
			order.setTax(restaurantinfo.getTax());
			order.setEstimatedTimeInMinutes(random_int);
			order.setOrderStatus(OrderStatus.PENDING);
			order.setUserId(orderRequest.getUserId());
			order.setRestaurantId(restaurantinfo.getRestaurantId());
			order.setOrderDishDetailList(orderDishDetailList);
			order.setPaymentType(orderRequest.getPaymentType());
			order.setNote(orderRequest.getInstruction());
			order.setOfferInPercentage(Integer.valueOf(restaurantinfo.getRestaurantOffer()));
			Order orderRes = dao.save(order);
			return "Order Sccessfully placed with id " + orderRes.getOrderId();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "Order Failed";
	}

	@SuppressWarnings("unused")
	private String saveOrder_Fallback(OrderRequest orderRequest) {
		System.out.println("Search Service is down!!! fallback route enabled...");
		return "CIRCUIT BREAKER ENABLED!!! No Response From Search Service at this moment.Sorry for inconvenience in placing Order "
				+ " Service will be back shortly - " + new Date();
	}

	@Override
	public void updateOrderStatus(Long orderId, String status) {
		Optional<Order> orderOpt = dao.findById(orderId);
		orderOpt.get().setOrderStatus(OrderStatus.valueOf(status));
		dao.save(orderOpt.get());
	}

}
