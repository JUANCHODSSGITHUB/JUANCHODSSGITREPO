package com.dss.dss3msloginv1.dto.util;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.entity.User;

public class UserDTOMapper {

    public static User mapUser(UserDTO userDTO){
        User user = new User(userDTO.getLoginId(),
                             userDTO.getPassword(),
                             userDTO.getEmail(),
                             userDTO.getFirstName(),
                             userDTO.getLastName(),
                             userDTO.getPhoneNumber());
        return user;
    }
}
