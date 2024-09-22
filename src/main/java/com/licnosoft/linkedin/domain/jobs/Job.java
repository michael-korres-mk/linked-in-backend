package com.licnosoft.linkedin.domain.jobs;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "jobs")
public class Job implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	@NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
	private String title;

	@Column(name = "description")
	@NotBlank(message = ValidationErrorMessages.DESCRIPTION_NOT_BLANK)
	private String description;

	@Column(name = "company")
	private String company;

	@Enumerated(EnumType.STRING)
	@Column(name = "workplace_type")
	@NotNull
	private WorkplaceType workplaceType;

	@Enumerated(EnumType.STRING)
	@Column(name = "employment_type")
	@NotNull
	private EmploymentType employmentType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "location")
	private String location;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getCompany() {return company;}

	public void setCompany(String company) {this.company = company;}

	public WorkplaceType getWorkplaceType() {return workplaceType;}

	public void setWorkplaceType(WorkplaceType workplaceType) {this.workplaceType = workplaceType;}

	public EmploymentType getEmploymentType() {return employmentType;}

	public void setEmploymentType(EmploymentType employmentType) {this.employmentType = employmentType;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public boolean isEnabled() {return enabled;}

	public void setEnabled(boolean enabled) {this.enabled = enabled;}

	public String getDescription() {return description;}

	public void setDescription(String description) {this.description = description;}

	public String getLocation() {return location;}

	public void setLocation(String location) {this.location = location;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Job job = (Job) o;
		return Objects.equals(id, job.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Job{" +
				"id=" + id +
				", title='" + title + '\'' +
				", company='" + company + '\'' +
				", workplaceType=" + workplaceType +
				", employmentType=" + employmentType +
				", creator=" + creator +
				", creationDate=" + creationDate +
				'}';
	}
}
