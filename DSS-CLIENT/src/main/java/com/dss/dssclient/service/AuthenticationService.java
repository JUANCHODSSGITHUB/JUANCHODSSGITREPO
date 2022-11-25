package com.dss.dssclient.service;

import com.dss.dssclient.proxy.AuthenticationProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationProxy authenticationProxy;

    public ResponseEntity<Boolean> loginUser(String userName, String password){
        return authenticationProxy.login(userName, password);
    }

}
