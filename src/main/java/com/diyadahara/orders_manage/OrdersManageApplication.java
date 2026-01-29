package com.diyadahara.orders_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrdersManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersManageApplication.class, args);
	}

}
