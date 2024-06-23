package com.licnosoft.linkedin.model.auth;


import java.util.Objects;

public class UserInfoDTO {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String role;
    protected String email;
    protected String password;

    public UserInfoDTO() {}

    public UserInfoDTO(String subject,String password, String firstName, String lastName, String role) {
        this.username = subject;
        this.password = password;
        this.firstName = Objects.equals(firstName, "null") ? null : firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return this.firstName == null ? this.lastName : this.firstName + " " + this.lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}