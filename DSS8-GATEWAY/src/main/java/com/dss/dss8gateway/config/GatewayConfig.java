package com.dss.dss8gateway.config;

import com.dss.dss8gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("ACTOR-OPERATIONS-SERVICE", r -> r.path("/dss/api/actor/**").filters(f -> f.filter(filter)).uri("http://localhost:9006/")).build();
               /* .route("alert", r -> r.path("/alert/**").filters(f -> f.filter(filter)).uri("lb://alert"))
                .route("echo", r -> r.path("/echo/**").filters(f -> f.filter(filter)).uri("lb://echo"))
                .route("hello", r -> r.path("/hello/**").filters(f -> f.filter(filter)).uri("lb://hello")).build();*/
    }

}
