package com.dss.dss3msloginv1.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class UserServiceTestConfig {

        @Bean
        protected UserService userService() {
            return new UserServiceImpl();
        }

}
