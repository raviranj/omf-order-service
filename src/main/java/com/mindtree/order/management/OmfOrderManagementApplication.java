package com.mindtree.order.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OmfOrderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmfOrderManagementApplication.class, args);
	}

}
