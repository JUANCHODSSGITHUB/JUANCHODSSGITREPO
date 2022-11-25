package com.dss.dssclient.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="API-GATEWAY")
public interface AuthenticationProxy {

    @PostMapping("/dss/api/user/login")
    public ResponseEntity<Boolean> login(@RequestHeader ("username") String id, @RequestHeader ("password") String password);


}