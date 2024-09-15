package com.licnosoft.linkedin.domain.jobs;

import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "job_applications")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	@NotNull
	private User creator;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	@NotNull
	private Job job;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "enabled")
	private boolean enabled;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getFirstName() {return firstName;}

	public void setFirstName(String firstName) {this.firstName = firstName;}

	public String getLastName() {return lastName;}

	public void setLastName(String lastName) {this.lastName = lastName;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public String getMobile() {return mobile;}

	public void setMobile(String mobile) {this.mobile = mobile;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public Job getJob() {return job;}

	public void setJob(Job job) {this.job = job;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public boolean isEnabled() {return enabled;}

	public void setEnabled(boolean enabled) {this.enabled = enabled;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		JobApplication that = (JobApplication) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "JobApplication{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", mobile='" + mobile + '\'' +
				", creator=" + creator +
				", creationDate=" + creationDate +
				", enabled=" + enabled +
				'}';
	}
}
