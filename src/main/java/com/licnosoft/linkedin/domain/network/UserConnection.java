package com.licnosoft.linkedin.domain.network;

import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_connections")
public class UserConnection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator_id")
	private User creator;

	@ManyToOne(fetch = FetchType.EAGER)
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

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public User getRecipient() {return recipient;}

	public void setRecipient(User recipient) {this.recipient = recipient;}

	public LocalDateTime getConnectionDate() {return connectionDate;}

	public void setConnectionDate(LocalDateTime connectionDate) {this.connectionDate = connectionDate;}

	public Chat getChat() {return chat;}

	public void setChat(Chat chat) {this.chat = chat;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserConnection that = (UserConnection) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "ConnectionRequest{" +
				"id=" + id +
				", creator=" + creator +
				", creationDate=" + creationDate +
				", recipient=" + recipient +
				'}';
	}
}
