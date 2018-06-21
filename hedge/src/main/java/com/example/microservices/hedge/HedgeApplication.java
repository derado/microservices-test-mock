package com.example.microservices.hedge;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.example.microservices"})
public class HedgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HedgeApplication.class, args);
	}
}
