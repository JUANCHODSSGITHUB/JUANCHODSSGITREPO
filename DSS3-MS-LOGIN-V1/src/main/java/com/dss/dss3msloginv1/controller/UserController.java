package com.dss.dss3msloginv1.controller;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dss/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> registerAccount(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable String id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Boolean> authenticateUser(@RequestHeader ("username") String id, @RequestHeader ("password") String password){
        return new ResponseEntity<>(userService.authenticate(id, password), HttpStatus.OK);
    }

    @PutMapping("/user/change-password")
    public ResponseEntity<String> changePassword(@RequestHeader ("username") String id, @RequestHeader ("password1") String password1, @RequestHeader ("password2") String password2){
        return new ResponseEntity<>(userService.changePassword(id, password1, password2), HttpStatus.OK);
    }

}
