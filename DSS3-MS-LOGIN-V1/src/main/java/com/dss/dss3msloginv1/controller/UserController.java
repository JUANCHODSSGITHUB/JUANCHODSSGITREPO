package com.dss.dss3msloginv1.controller;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.entity.User;
import com.dss.dss3msloginv1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dss/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public String registerAccount(@RequestBody UserDTO user){
        return userService.addUser(user);
    }

    @DeleteMapping("/user/{id}")
    public String deleteAccount(@PathVariable String id){
        return userService.deleteUser(id);
    }

    @PostMapping("/user/login")
    public Boolean authenticateUser(@RequestHeader ("username") String id, @RequestHeader ("password") String password){
        return userService.authenticate(id, password);
    }

    @PutMapping("/user/changePassword")
    public String changePassword(@RequestHeader ("username") String id, @RequestHeader ("password1") String password1, @RequestHeader ("password2") String password2){
        return userService.changePassword(id, password1, password2);
    }
}
