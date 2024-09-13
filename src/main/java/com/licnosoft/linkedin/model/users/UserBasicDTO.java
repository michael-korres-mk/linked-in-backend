package com.licnosoft.linkedin.model.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.model.general.EntityDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserBasicDTO extends EntityDTO {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected UserRole userRole;
    protected String email;
    protected String mobile;
    protected boolean enabled;
    // TODO: Add enabled/disabled (public/private)
    protected String workExperience;
    protected String education;
    protected String jobTitle;
    protected String company;
    protected String skills;
    protected String token;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
    protected LocalDateTime creationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
    protected LocalDateTime lastVisit;
    protected Integer failedLoginAttempts = 0;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = Constants.APP_TIMEZONE)
    protected LocalDate birthDate;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return getName();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
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

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getMobile() {return mobile;}

    public void setMobile(String mobile) {this.mobile = mobile;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public String getWorkExperience() {return workExperience;}

    public void setWorkExperience(String workExperience) {this.workExperience = workExperience;}

    public String getEducation() {return education;}

    public void setEducation(String education) {this.education = education;}

    public String getJobTitle() {return jobTitle;}

    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}

    public String getCompany() {return company;}

    public void setCompany(String company) {this.company = company;}

    public String getSkills() {return skills;}

    public void setSkills(String skills) {this.skills = skills;}

    public UserRole getUserRole() {return userRole;}

    public void setUserRole(UserRole userRole) {this.userRole = userRole;}
}
