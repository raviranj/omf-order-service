package com.mindtree.order.management.util;

import lombok.Data;

@Data
public class UserDto {

	private Long userId;

	private String name;

	private Long contactNumber;

	private String email;

	private String password;

}
