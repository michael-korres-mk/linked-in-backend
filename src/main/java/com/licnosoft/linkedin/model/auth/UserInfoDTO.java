package com.licnosoft.linkedin.model.auth;


import com.licnosoft.linkedin.model.users.UserDTO;

public class UserInfoDTO extends UserDTO {
    protected String password;
    protected String confirmPassword;

    public UserInfoDTO() {}

    public UserInfoDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getConfirmPassword() {return confirmPassword;}

    public void setConfirmPassword(String confirmPassword) {this.confirmPassword = confirmPassword;}
}