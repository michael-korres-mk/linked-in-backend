package com.licnosoft.linkedin.domain.posts;

import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public Post getPost() {return post;}

	public void setPost(Post post) {this.post = post;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Like like = (Like) o;
		return Objects.equals(id, like.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Like{" +
				"id=" + id +
				'}';
	}
}
