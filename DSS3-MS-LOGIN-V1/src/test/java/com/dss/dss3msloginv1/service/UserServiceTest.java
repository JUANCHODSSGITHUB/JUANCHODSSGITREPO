package com.dss.dss3msloginv1.service;


import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.entity.User;
import com.dss.dss3msloginv1.exception.AdminAlreadyExistsException;
import com.dss.dss3msloginv1.exception.LoginFailedException;
import com.dss.dss3msloginv1.exception.UserNotFoundException;
import com.dss.dss3msloginv1.repository.UserRepository;
import com.dss.dss3msloginv1.util.TokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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


    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJVc2VyIFNlcnZpY2UiLCJzdWIiOiI5MTcyZGUyNS0zMTBlLTRmNGUtYjkyMC05MGVhMzc2YzcwZDMiLCJpYXQiOjE2Njc4MDM1MjMsImV4cCI6MTY2NzgwNDQyMywidXNlciI6InJ1c3NlckBnbWFpbC5jb20ifQ.AquWFWo6d3wPnWjtSy7jpgovEbkemeN56Cbg1YX8zuhytbz8ADNBWZqn7Y6qRyuA2dniG_W4mjWhw56TIZB3fw";


    @Test
    public void incorrectPassword() {
        User user = new User("jrvm"
                , UserService.encryptPassword("Passw0rd!")
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
        String username = "jrvm";
        String password = "Password!";

        Assertions.assertThrows(LoginFailedException.class, () -> userService.authenticate(username, password));
    }

   @Test
    public void correctPassword() {
        User user = new User("jrvm"
                , UserService.encryptPassword("Passw0rd!")
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
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

        Mockito.when(userRepository.findByUserId(user.getUserId())).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Assertions.assertThrows(AdminAlreadyExistsException.class,() -> userService.addUser(userDTO));
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

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm2"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Assertions.assertThrows(AdminAlreadyExistsException.class,() -> userService.addUser(userDTO));
    }

    @Test
    public void unsuccessfulRegistration3() {
        //phone number already registered
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByPhoneNumber(user.getPhoneNumber())).thenReturn(user);

        UserDTO userDTO = new UserDTO("jrvm2"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Assertions.assertThrows(AdminAlreadyExistsException.class,() -> userService.addUser(userDTO));
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


    @Test
    public void successfulDeletion() {
        //phone number already registered
        User user = new User("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByUserId(user.getUserId())).thenReturn(user);

        Assertions.assertEquals("Data successfully deleted.", userService.deleteUser("jrvm"));
    }

    @Test
    public void unsuccessfulChangePassword() {
        User user = new User("jrvm"
                , UserService.encryptPassword("Passw0rd!")
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
        String username = "jrvm";
        String password = "Pa5sw0rd!";
        String password2 = "P4ssw0rd!";
        Assertions.assertThrows(LoginFailedException.class, () ->userService.changePassword(username, password, password2));
    }

    @Test
    public void successfulChangePassword() {
        User user = new User("jrvm"
                , UserService.encryptPassword("Passw0rd!")
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
        String username = "jrvm";
        String password = "Passw0rd!";
        String password2 = "P4ssw0rd!";
        Assertions.assertEquals("Password changed successfully.", userService.changePassword(username, password, password2));
    }

    @Test
    public void successfulChangePasswordEmail() {
        User user = new User("jrvm"
                , UserService.encryptPassword("Passw0rd!")
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Mockito.when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        String username = "jr@mail.com";
        String password = "Passw0rd!";
        String password2 = "P4ssw0rd!";
        Assertions.assertEquals("Password changed successfully.", userService.changePassword(username, password, password2));
    }




}
