package com.licnosoft.linkedin.domain;

import com.licnosoft.linkedin.domain.users.UserContact;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "chat")
	private UserContact contact;

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public UserContact getContact() {return contact;}

	public void setContact(UserContact contact) {this.contact = contact;}

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
