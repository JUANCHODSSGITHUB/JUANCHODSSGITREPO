package com.dss.dss3msloginv1.controller;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.exception.AdminAlreadyExistsException;
import com.dss.dss3msloginv1.exception.LoginFailedException;
import com.dss.dss3msloginv1.exception.UserNotFoundException;
import com.dss.dss3msloginv1.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private static final String TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJVc2VyIFNlcnZpY2UiLCJzdWIiOiI5MTcyZGUyNS0zMTBlLTRmNGUtYjkyMC05MGVhMzc2YzcwZDMiLCJpYXQiOjE2Njc4MDM1MjMsImV4cCI6MTY2NzgwNDQyMywidXNlciI6InJ1c3NlckBnbWFpbC5jb20ifQ.AquWFWo6d3wPnWjtSy7jpgovEbkemeN56Cbg1YX8zuhytbz8ADNBWZqn7Y6qRyuA2dniG_W4mjWhw56TIZB3fw";

    @Test
    void successfulRegisterAccount() throws Exception {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.addUser(userDTO)).thenReturn("Account successfully registered.");

       this.mockMvc.perform(post("/dss/api/user").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString((userDTO))))
                        .andExpect(status().isCreated());

    }

    @Test
    void failRegisterAccount() throws Exception {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.addUser(any(UserDTO.class))).thenThrow(new AdminAlreadyExistsException("Username/email already exists."));

        this.mockMvc.perform(post("/dss/api/user").contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString((userDTO))))
                        .andExpect(status().isConflict());

    }

    @Test
    void successfulDeleteAccount() throws Exception {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.deleteUser(any(String.class))).thenReturn("Data successfully deleted.");

        this.mockMvc.perform(delete("/dss/api/user/{id}", userDTO.getLoginId()).contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void failDeleteAccount() throws Exception {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.deleteUser(any(String.class))).thenThrow(new UserNotFoundException("No such account with username = " + userDTO.getLoginId() + "."));

        this.mockMvc.perform(delete("/dss/api/user/{id}", userDTO.getLoginId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void successfulAuthenticateUser() throws Exception{
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.authenticate(any(String.class), any(String.class))).thenReturn(TOKEN);

        this.mockMvc.perform(post("/dss/api/user/login").contentType(MediaType.APPLICATION_JSON)
                        .header("username", userDTO.getLoginId())
                        .header("password", userDTO.getPassword()))
                        .andExpect(status().isOk());

    }

    @Test
    void failAuthenticateUser() throws Exception{
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.authenticate(any(String.class), any(String.class))).thenThrow(new LoginFailedException("Incorrect username/password."));

        this.mockMvc.perform(post("/dss/api/user/login").contentType(MediaType.APPLICATION_JSON)
                        .header("username", userDTO.getLoginId())
                        .header("password", userDTO.getPassword()))
                .andExpect(status().isForbidden());

    }

    @Test
    void failChangePassword() throws Exception{
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.changePassword(any(String.class), any(String.class), any(String.class))).thenThrow(new LoginFailedException("Incorrect username/password."));

        this.mockMvc.perform(put("/dss/api/user/change-password").contentType(MediaType.APPLICATION_JSON)
                        .header("username", userDTO.getLoginId())
                        .header("password1", userDTO.getPassword())
                        .header("password2", "P@5sword"))
                .andExpect(status().isForbidden());
    }
    @Test
    void successfulChangePassword() throws Exception{
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jrv@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102828");

        Mockito.when(userService.changePassword(any(String.class), any(String.class), any(String.class))).thenReturn("Password changed successfully.");

        this.mockMvc.perform(put("/dss/api/user/change-password").contentType(MediaType.APPLICATION_JSON)
                        .header("username", userDTO.getLoginId())
                        .header("password1", userDTO.getPassword())
                        .header("password2", "P@5sword"))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
