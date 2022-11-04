package com.dss.dss3msloginv1.inputValidation;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.dto.util.UserDTOChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDTOValidationTest {
    @Test
    public void incorrectPasswordFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTOPassword(userDTO.getPassword());
        Assertions.assertEquals(result, "Password must contain at least 1 digit, 1 uppercase letter, and 1 special character.");
    }

    @Test
    public void correctPasswordFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd!"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "09212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTOPassword(userDTO.getPassword());
        Assertions.assertNull(result);
    }

    @Test
    public void incorrectFNameFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho1"
                , "Meneses"
                , "09212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTO(userDTO).get(0);
        Assertions.assertEquals(result, "Only letters are allowed in name fields.");
    }

    @Test
    public void incorrectLNameFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses1"
                , "09212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTO(userDTO).get(0);
        Assertions.assertEquals(result, "Only letters are allowed in name fields.");
    }

    @Test
    public void incorrectPhoneFormatValidation() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "9212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTO(userDTO).get(0);
        Assertions.assertEquals(result, "Only 11 digits are allowed in phone number field.");
    }

    @Test
    public void incorrectPhoneFormatValidation2() {
        UserDTO userDTO = new UserDTO("jrvm"
                , "Passw0rd"
                , "jr@mail.com"
                , "Juancho"
                , "Meneses"
                , "t9212102824");

        UserDTOChecker validate = new UserDTOChecker();
        String result = validate.validateUserDTO(userDTO).get(0);
        Assertions.assertEquals(result, "Only 11 digits are allowed in phone number field.");
    }



}
