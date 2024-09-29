package com.licnosoft.linkedin.domain.users;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.jobs.Job;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "skills")
public class Skill implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	@NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "job_id")
	private Job job;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public Job getJob() {return job;}

	public void setJob(Job job) {this.job = job;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Skill skill = (Skill) o;
		return Objects.equals(id, skill.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Skill{" +
				"id=" + id +
				", title='" + title + '\'' +
				", creator=" + creator +
				", creationDate=" + creationDate +
				'}';
	}
}
