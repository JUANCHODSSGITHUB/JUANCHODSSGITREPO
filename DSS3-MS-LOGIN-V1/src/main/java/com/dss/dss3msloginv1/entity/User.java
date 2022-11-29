package com.dss.dss3msloginv1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USERS")
@Entity
public class User {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name="EMAIL", nullable = false)
    private String email;

    @Column(name="FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name="LAST_NAME", nullable = false)
    private String lastName;

    @Column(name="PHONE_NUMBER", nullable = false)
    private String phoneNumber;


}
