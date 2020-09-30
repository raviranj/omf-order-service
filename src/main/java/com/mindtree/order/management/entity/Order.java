package com.mindtree.order.management.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.mindtree.order.management.util.OrderStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "order_info")
public class Order implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderId")
	private Long orderId;

	@Column(name = "userId")
	private Long userId;

	@Column(name = "paymentType")
	private String paymentType;

	@Enumerated(EnumType.STRING)
	@Column(name = "orderStatus", length = 15)
	private OrderStatus orderStatus;

	@Column(name = "totalCost")
	private double totalCost;

	@Column(name = "note")
	private String note;

	@Column(name = "estimatedTimeInMinutes")
	private int estimatedTimeInMinutes;

	@Column(name = "offerInPercentage")
	private int offerInPercentage;

	@Column(name = "restaurantId")
	private Long restaurantId;

	@Column(name = "restaurantName")
	private String restaurantName;

	@Column(name = "deliveryPerson")
	private String deliveryPerson;

	@Column(name = "tax")
	private double tax;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderDishDetail> orderDishDetailList;

	@Column
	@CreationTimestamp
	private LocalDateTime creationTime;

	@Column
	@LastModifiedDate
	private Date lastModifyTime;
}
