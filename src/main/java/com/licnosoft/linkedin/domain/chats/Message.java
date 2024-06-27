package com.licnosoft.linkedin.domain.chats;

import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id")
	private User sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_id")
	private User recipient;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "message")
	private String message;

	@Column(name = "delivered")
	private boolean delivered;
	@Column(name = "read")
	private boolean read;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chat_id")
	private Chat chat;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public User getSender() {return sender;}

	public void setSender(User sender) {this.sender = sender;}

	public User getRecipient() {return recipient;}

	public void setRecipient(User recipient) {this.recipient = recipient;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public String getMessage() {return message;}

	public void setMessage(String message) {this.message = message;}

	public boolean isDelivered() {return delivered;}

	public void setDelivered(boolean delivered) {this.delivered = delivered;}

	public boolean isRead() {return read;}

	public void setRead(boolean read) {this.read = read;}

	public Chat getChat() {return chat;}

	public void setChat(Chat chat) {this.chat = chat;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Message message = (Message) o;
		return Objects.equals(id, message.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", sender=" + sender +
				", recipient=" + recipient +
				", creationDate=" + creationDate +
				", message='" + message + '\'' +
				", delivered=" + delivered +
				", read=" + read +
				'}';
	}
}
