package com.dss.dss3msloginv1.service;


import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.entity.User;
import com.dss.dss3msloginv1.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@Import(UserServiceTestConfig.class)
public class UserServiceTest {


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Test
    public void incorrectPassword() {
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        String username = "jrvm";
        String password = "Password!";

        Assertions.assertFalse(userService.authenticate(username, password));
    }

    @Test
    public void correctPassword() {
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.save(user)).thenReturn(user);


        String username = "jrvm";
        String password = "Passw0rd!";

        Assertions.assertTrue(userService.authenticate(username, password));
    }


    @Test
    public void unsuccessfulRegistration() {
        //username already exists
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Assertions.assertEquals("Username/email already exists.",userService.addUser(userDTO));
    }

    @Test
    public void unsuccessfulRegistration2() {
        //phone number already registered
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm2"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Assertions.assertEquals("Phone number is already registered.", userService.addUser(userDTO));
    }

    @Test
    public void successfulRegistration() {
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm2"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Assertions.assertEquals("Account successfully registered.", userService.addUser(userDTO));
    }
}
