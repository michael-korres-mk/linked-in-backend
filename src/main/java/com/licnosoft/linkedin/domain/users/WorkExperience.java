package com.licnosoft.linkedin.domain.users;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "work_experiences")
public class WorkExperience implements Serializable {

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

	@Column(name = "location")
	@NotNull
	private String location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "start_date")
	@NotNull(message = ValidationErrorMessages.START_DATE_NOT_BLANK)
	private LocalDate startDate;

	@Column(name = "end_date")
	@NotNull(message = ValidationErrorMessages.END_DATE_NOT_BLANK)
	private LocalDate endDate;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getDescription() {return description;}

	public void setDescription(String description) {this.description = description;}

	public String getCompany() {return company;}

	public void setCompany(String company) {this.company = company;}

	public WorkplaceType getWorkplaceType() {return workplaceType;}

	public void setWorkplaceType(WorkplaceType workplaceType) {this.workplaceType = workplaceType;}

	public EmploymentType getEmploymentType() {return employmentType;}

	public void setEmploymentType(EmploymentType employmentType) {this.employmentType = employmentType;}

	public String getLocation() {return location;}

	public void setLocation(String location) {this.location = location;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public boolean isEnabled() {return enabled;}

	public void setEnabled(boolean enabled) {this.enabled = enabled;}

	public LocalDate getStartDate() {return startDate;}

	public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

	public LocalDate getEndDate() {return endDate;}

	public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WorkExperience that = (WorkExperience) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "WorkExperience{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", company='" + company + '\'' +
				", workplaceType=" + workplaceType +
				", employmentType=" + employmentType +
				", location='" + location + '\'' +
				", creator=" + creator +
				", creationDate=" + creationDate +
				", enabled=" + enabled +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
