package com.licnosoft.linkedin.domain.posts;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.general.PostPhoto;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	@NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
	private String title;

	@Column(name = "description")
	@NotBlank(message = ValidationErrorMessages.DESCRIPTION_NOT_BLANK)
	private String description;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();

	@Column(name = "modification_date")
	private LocalDateTime modificationDate = LocalDateTime.now();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Like> likes = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<PostPhoto> photos = new ArrayList<>();

	@Column(name = "is_job_opening")
	private boolean jobOpening;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getDescription() {return description;}

	public void setDescription(String description) {this.description = description;}

	public boolean isEnabled() {return enabled;}

	public void setEnabled(boolean enabled) {this.enabled = enabled;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public LocalDateTime getModificationDate() {return modificationDate;}

	public void setModificationDate(LocalDateTime modificationDate) {this.modificationDate = modificationDate;}

	public List<Comment> getComments() {return comments;}

	public void setComments(List<Comment> comments) {this.comments = comments;}

	public List<Like> getLikes() {return likes;}

	public void setLikes(List<Like> likes) {this.likes = likes;}

	public boolean isJobOpening() {return jobOpening;}

	public void setJobOpening(boolean jobOpening) {this.jobOpening = jobOpening;}

	public List<PostPhoto> getPhotos() {return photos;}

	public void setPhotos(List<PostPhoto> photos) {this.photos = photos;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Post post = (Post) o;
		return Objects.equals(id, post.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
