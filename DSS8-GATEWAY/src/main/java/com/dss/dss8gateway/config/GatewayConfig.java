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
        return builder.routes().route("ACTOR-OPERATIONS-SERVICE", r -> r.path("/dss/api/actor/**").filters(f -> f.filter(filter)).uri("lb://ACTOR-OPERATIONS-SERVICE"))
                .route("MOVIE-OPERATIONS-SERVICE", r -> r.path("/dss/api/movie/**").filters(f -> f.filter(filter)).uri("lb://MOVIE-OPERATIONS-SERVICE"))
                .route("REVIEW-OPERATIONS-SERVICE", r -> r.path("/dss/api/review/**").filters(f -> f.filter(filter)).uri("lb://REVIEW-OPERATIONS-SERVICE")).build();
    }

}
