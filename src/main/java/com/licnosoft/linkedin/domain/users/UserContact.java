package com.licnosoft.linkedin.domain.users;

import com.licnosoft.linkedin.domain.chats.Chat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_contacts")
public class UserContact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_id")
	private User recipient;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;
	@Column(name = "connection_date")
	private LocalDateTime connectionDate;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_id")
	private Chat chat;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public User getUser() {return user;}

	public void setUser(User user) {this.user = user;}

	public User getRecipient() {return recipient;}

	public void setRecipient(User contact) {this.recipient = contact;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public Chat getChat() {return chat;}

	public void setChat(Chat chat) {this.chat = chat;}

	public LocalDateTime getConnectionDate() {return connectionDate;}

	public void setConnectionDate(LocalDateTime connectionDate) {this.connectionDate = connectionDate;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserContact that = (UserContact) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "UserContact{" +
				"id=" + id +
				", user=" + user +
				", contact=" + recipient +
				'}';
	}
}
