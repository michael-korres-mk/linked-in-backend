package com.licnosoft.linkedin.domain.users;

import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.domain.general.PlainEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails, PlainEntity<User> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @NotNull
    private UserRole userRole;

    @Column(name = "username")
    @NotNull
    private String username;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "company")
    private String company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserConnection> contacts = new ArrayList<>();

    @Column(name = "workExperience")
    private String workExperience;

    @Column(name = "education")
    private String education;

    @Column(name = "skills")
    private String skills;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> authorities = new HashSet<>();
        authorities.add(userRole);
        return authorities;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public UserRole getUserRole() {return userRole;}

    public void setUserRole(UserRole userRole) {this.userRole = userRole;}

    @Override
    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    @Override
    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getMobile() {return mobile;}

    public void setMobile(String mobile) {this.mobile = mobile;}

    public void setEnabled(boolean enabled) {this.enabled = enabled;}

    public List<UserConnection> getContacts() {return contacts;}

    public void setContacts(List<UserConnection> contacts) {this.contacts = contacts;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User entity = (User) o;
        if (entity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userRole=" + userRole +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
