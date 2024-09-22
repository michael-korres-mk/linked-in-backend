package com.licnosoft.linkedin.domain.users;

import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.domain.general.File;
import com.licnosoft.linkedin.domain.general.PlainEntity;
import com.licnosoft.linkedin.domain.jobs.Job;
import com.licnosoft.linkedin.domain.network.UserConnection;
import com.licnosoft.linkedin.domain.posts.Comment;
import com.licnosoft.linkedin.domain.posts.Like;
import com.licnosoft.linkedin.domain.posts.Post;
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

    @Column(name = "about_me")
    private String aboutMe;

    @OneToOne
    @JoinColumn(name = "profile_photo")
    private File profilePhoto;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<UserConnection> connections = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "creator", cascade = CascadeType.ALL)
    private List<WorkExperience> workExperiences = new ArrayList<>();

    @Column(name = "skills_enabled")
    private boolean skillsEnabled;
    @Column(name = "educations_enabled")
    private boolean educationsEnabled;
    @Column(name = "work_experiences_enabled")
    private boolean workExperiencesEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> authorities = new HashSet<>();
        authorities.add(userRole);
        return authorities;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
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

    @Override
    public boolean isEnabled() {return enabled;}

    public void setEnabled(boolean enabled) {this.enabled = enabled;}

    public String getJobTitle() {return jobTitle;}

    public void setJobTitle(String jobTitle) {this.jobTitle = jobTitle;}

    public String getCompany() {return company;}

    public void setCompany(String company) {this.company = company;}

    public String getAboutMe() {return aboutMe;}

    public void setAboutMe(String aboutMe) {this.aboutMe = aboutMe;}

    public List<UserConnection> getConnections() {return connections;}

    public void setConnections(List<UserConnection> connections) {this.connections = connections;}

    public List<Like> getLikes() {return likes;}

    public void setLikes(List<Like> likes) {this.likes = likes;}

    public List<Comment> getComments() {return comments;}

    public void setComments(List<Comment> comments) {this.comments = comments;}

    public List<Post> getPosts() {return posts;}

    public void setPosts(List<Post> posts) {this.posts = posts;}

    public List<Job> getJobs() {return jobs;}

    public void setJobs(List<Job> jobs) {this.jobs = jobs;}

    public List<Skill> getSkills() {return skills;}

    public void setSkills(List<Skill> skills) {this.skills = skills;}

    public List<Education> getEducations() {return educations;}

    public void setEducations(List<Education> educations) {this.educations = educations;}

    public List<WorkExperience> getWorkExperiences() {return workExperiences;}

    public void setWorkExperiences(List<WorkExperience> workExperiences) {this.workExperiences = workExperiences;}

    public boolean isSkillsEnabled() {return skillsEnabled;}

    public void setSkillsEnabled(boolean skillsEnabled) {this.skillsEnabled = skillsEnabled;}

    public boolean isEducationsEnabled() {return educationsEnabled;}

    public void setEducationsEnabled(boolean educationsEnabled) {this.educationsEnabled = educationsEnabled;}

    public boolean isWorkExperiencesEnabled() {return workExperiencesEnabled;}

    public void setWorkExperiencesEnabled(boolean workExperiencesEnabled) {this.workExperiencesEnabled = workExperiencesEnabled;}

    public File getProfilePhoto() {return profilePhoto;}

    public void setProfilePhoto(File profilePhoto) {this.profilePhoto = profilePhoto;}


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
