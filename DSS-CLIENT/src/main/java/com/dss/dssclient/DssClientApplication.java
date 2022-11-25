package com.dss.dssclient;

import com.dss.dssclient.service.AuthenticationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.sql.SQLOutput;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class DssClientApplication {

	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(DssClientApplication.class, args);
		AuthenticationService authenticationService = applicationContext.getBean("authenticationService", AuthenticationService.class);
		System.out.println(authenticationService.loginUser("jrv", "P@ssw0rd"));
	}

}
