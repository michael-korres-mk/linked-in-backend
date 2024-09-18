package com.licnosoft.linkedin.domain.chats;

import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chats")
public class Chat {

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

	@OneToOne
	@JoinColumn(name = "last_message_id")
	private Message lastMessage;

	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public List<Message> getMessages() {return messages;}

	public void setMessages(List<Message> messages) {this.messages = messages;}

	public Message getLastMessage() {return lastMessage;}

	public void setLastMessage(Message lastMessage) {this.lastMessage = lastMessage;}

	public User getCreator() {return creator;}

	public void setCreator(User creator) {this.creator = creator;}

	public User getRecipient() {return recipient;}

	public void setRecipient(User recipient) {this.recipient = recipient;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Chat chat = (Chat) o;
		return Objects.equals(id, chat.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Chat{" +
				"id=" + id +
				'}';
	}
}
