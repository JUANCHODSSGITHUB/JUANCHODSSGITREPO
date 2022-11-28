package com.dss.dss5msactorv1.dto;


public class ActorDto {
    private int actorId;

    private String firstName;

    private String lastName;

    private int age;

    private char gender;

    @SuppressWarnings("unchecked")
    public int getActorId() {
        return actorId;
    }

    @SuppressWarnings("unchecked")
    public void setActorId(int actorId) {
        this.actorId = actorId;
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
