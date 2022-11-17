package com.dss.dss3msloginv1.inputValidation;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.util.UserDTOChecker;
import com.dss.dss3msloginv1.exception.InvalidInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDTOValidationTest {
    @Test
    void incorrectPasswordFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        Assertions.assertThrows(InvalidInputException.class,() -> UserDTOChecker.validateUserDTOPassword(userDTO.getPassword()));
    }

    @Test
    void correctPasswordFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");


        String result = UserDTOChecker.validateUserDTOPassword(userDTO.getPassword());
        Assertions.assertNull(result);
    }

    @Test
    void incorrectFNameFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho1"
                , "Meneses"
                , "09212102824");

        Assertions.assertThrows(InvalidInputException.class,() -> UserDTOChecker.validateUserDTO(userDTO));
    }

    @Test
    void incorrectLNameFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses1"
                , "09212102824");

        Assertions.assertThrows(InvalidInputException.class,() -> UserDTOChecker.validateUserDTO(userDTO));
    }

    @Test
    void incorrectPhoneFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "9212102824");

        Assertions.assertThrows(InvalidInputException.class,() -> UserDTOChecker.validateUserDTO(userDTO));
    }

    @Test
    void incorrectPhoneFormatValidation2() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "t9212102824");

        Assertions.assertThrows(InvalidInputException.class,() -> UserDTOChecker.validateUserDTO(userDTO));
    }



}
