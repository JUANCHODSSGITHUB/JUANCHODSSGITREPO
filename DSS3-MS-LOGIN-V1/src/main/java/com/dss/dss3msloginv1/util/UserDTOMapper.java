package com.dss.dss3msloginv1.util;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.entity.User;

public class UserDTOMapper {
    private UserDTOMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User mapUser(UserDTO userDTO){

        return new User(userDTO.getLoginId(),
                userDTO.getPassword(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhoneNumber());
    }
}
