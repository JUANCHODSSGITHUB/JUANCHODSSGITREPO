package com.dss.dss3msloginv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String loginId;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;


}
