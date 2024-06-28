package com.licnosoft.linkedin.model.auth;


import com.licnosoft.linkedin.model.general.EntityDTO;

public class UserInfoDTO extends EntityDTO {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String role;
    protected String mobile;
    protected String email;
    protected String password;
    protected String confirmPassword;

    public UserInfoDTO() {}

    public UserInfoDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getMobile() {return mobile;}

    public void setMobile(String mobile) {this.mobile = mobile;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getConfirmPassword() {return confirmPassword;}

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}
}