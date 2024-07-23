package com.licnosoft.linkedin.domain.chats;

import com.licnosoft.linkedin.domain.users.UserContact;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "chat")
	private UserContact contact;

	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "message_id")
	private Message lastMessage;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public UserContact getContact() {return contact;}

	public void setContact(UserContact contact) {this.contact = contact;}

	public List<Message> getMessages() {return messages;}

	public void setMessages(List<Message> messages) {this.messages = messages;}

	public Message getLastMessage() {return lastMessage;}

	public void setLastMessage(Message lastMessage) {this.lastMessage = lastMessage;}

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
				", contact=" + contact +
				'}';
	}
}
