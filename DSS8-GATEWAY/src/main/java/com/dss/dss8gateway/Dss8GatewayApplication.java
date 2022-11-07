package com.dss.dss8gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Dss8GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dss8GatewayApplication.class, args);
	}

}
