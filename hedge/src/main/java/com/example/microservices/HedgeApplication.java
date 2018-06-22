package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class HedgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HedgeApplication.class, args);
	}
}
