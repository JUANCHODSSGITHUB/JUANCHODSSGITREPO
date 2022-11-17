package com.dss.dss3msloginv1.util;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class UserDTOChecker {

    private static final String NAME_PATTERN = "^[A-Za-z]+$";
    private static final String NUMBER_PATTERN = "^\\d{11}$";
    private static final String PW_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private UserDTOChecker() {
        throw new IllegalStateException("Utility class");
    }
    public static List<String> validateUserDTO(UserDTO userDTO) throws InvalidInputException{
        List<String> validationMessage = new ArrayList<>();

        if(userDTO.getFirstName()!= null && userDTO.getLastName()!= null){
            Pattern namePattern = Pattern.compile(NAME_PATTERN);
            if(!namePattern.matcher(userDTO.getFirstName()).matches() || !namePattern.matcher(userDTO.getLastName()).matches()){
                validationMessage.add("Only letters are allowed in name fields.");
                throw new InvalidInputException(validationMessage.get(validationMessage.size()-1));
            }
        }else{
            validationMessage.add("Invalid null inputs for name fields.");
            throw new InvalidInputException(validationMessage.get(validationMessage.size()-1));
        }

        if(userDTO.getPhoneNumber() != null){
            Pattern phonePattern = Pattern.compile(NUMBER_PATTERN);
            if(!phonePattern.matcher(userDTO.getPhoneNumber()).matches() ){
                validationMessage.add("Only 11 digits are allowed in phone number field.");
                throw new InvalidInputException(validationMessage.get(validationMessage.size()-1));
            }
        }else{
            validationMessage.add("Invalid null input for phone number field.");
            throw new InvalidInputException(validationMessage.get(validationMessage.size()-1));
        }


        if(userDTO.getEmail() == null || userDTO.getPhoneNumber() == null || userDTO.getLoginId() == null){
            validationMessage.add("Invalid null inputs for fields.");
            throw new InvalidInputException(validationMessage.get(validationMessage.size()-1));
        }

        return validationMessage;

    }

    public static String validateUserDTOPassword(String password) throws InvalidInputException{
      String  validationMessage = null;

     if(password != null){
        Pattern passwordPattern = Pattern.compile(PW_PATTERN);
        if(!passwordPattern.matcher(password).matches() ){
            validationMessage = ("Password must contain at least 1 digit, 1 uppercase letter, and 1 special character.");
            throw new InvalidInputException(validationMessage);
        }
        }else{
        validationMessage = ("Invalid null input for password field.");
        }

     return validationMessage;
}


}
