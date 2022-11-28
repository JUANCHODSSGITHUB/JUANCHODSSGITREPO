package com.dss.dss4msmoviev1.dto;


public class ActorDTO {


    private String firstName;

    private String lastName;

    private int age;

    private char gender;

    public ActorDTO() {
    }

    public ActorDTO(String firstName, String lastName, int age, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
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
    public int getAge() {
        return age;
    }
    @SuppressWarnings("unchecked")
    public void setAge(int age) {
        this.age = age;
    }
    @SuppressWarnings("unchecked")
    public char getGender() {
        return gender;
    }
    @SuppressWarnings("unchecked")
    public void setGender(char gender) {
        this.gender = gender;
    }

}
