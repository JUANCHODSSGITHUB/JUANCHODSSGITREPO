package com.dss.dss3msloginv1.dto;

public class UserDTO {

    private String loginId;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;


    public UserDTO(String loginId, String password, String email, String firstName, String lastName, String phoneNumber) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    @SuppressWarnings("unchecked")
    public String getEmail() {
        return email;
    }
    @SuppressWarnings("unchecked")
    public void setEmail(String email) {
        this.email = email;
    }
    @SuppressWarnings("unchecked")
    public String getFirstName() {
        return firstName;
    }
    @SuppressWarnings("unchecked")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @SuppressWarnings("unchecked")
    public String getLastName() {
        return lastName;
    }
    @SuppressWarnings("unchecked")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @SuppressWarnings("unchecked")
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @SuppressWarnings("unchecked")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @SuppressWarnings("unchecked")
    public String getLoginId() {
        return loginId;
    }
    @SuppressWarnings("unchecked")
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
    @SuppressWarnings("unchecked")
    public String getPassword() {
        return password;
    }
    @SuppressWarnings("unchecked")
    public void setPassword(String password) {
        this.password = password;
    }
}
