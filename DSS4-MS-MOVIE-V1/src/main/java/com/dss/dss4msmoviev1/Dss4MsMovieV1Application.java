package com.dss.dss4msmoviev1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Dss4MsMovieV1Application {

    public static void main(String[] args) {
        SpringApplication.run(Dss4MsMovieV1Application.class, args);
    }

}
