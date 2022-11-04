package com.dss.dss3msloginv1.dto.util;

import com.dss.dss3msloginv1.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class UserDTOChecker {

    public static List<String> validateUserDTO(UserDTO userDTO){
        List<String> validationMessage = new ArrayList<>();

        if(userDTO.getFirstName()!= null && userDTO.getLastName()!= null){
            Pattern namePattern = Pattern.compile("^[A-Za-z]+$");
            if(!namePattern.matcher(userDTO.getFirstName()).matches() || !namePattern.matcher(userDTO.getLastName()).matches()){
                validationMessage.add("Only letters are allowed in name fields.");
            }
        }else{
            validationMessage.add("Invalid null inputs for name fields.");
        }

        if(userDTO.getPhoneNumber() != null){
            Pattern phonePattern = Pattern.compile("^\\d{11}$");
            if(!phonePattern.matcher(userDTO.getPhoneNumber()).matches() ){
                validationMessage.add("Only 11 digits are allowed in phone number field.");
            }
        }else{
            validationMessage.add("Invalid null input for phone number field.");
        }



        if(userDTO.getEmail() == null || userDTO.getPhoneNumber() == null || userDTO.getLoginId() == null){
            validationMessage.add("Invalid null inputs for fields.");
        }

        return validationMessage;

    }

    public static String validateUserDTOPassword(String password){
      String  validationMessage = null;

     if(password != null){
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        if(!passwordPattern.matcher(password).matches() ){
            validationMessage = ("Password must contain at least 1 digit, 1 uppercase letter, and 1 special character.");
        }
        }else{
        validationMessage = ("Invalid null input for password field.");
        }

     return validationMessage;
}
}
