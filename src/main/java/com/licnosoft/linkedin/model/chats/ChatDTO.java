package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.users.UserConnectionDTO;
import java.util.List;

public class ChatDTO extends EntityDTO {

	private UserConnectionDTO contact;
	private List<MessageDTO> messages;

	private MessageDTO lastMessage;

	private String token;

	public UserConnectionDTO getContact() {return contact;}

	public void setContact(UserConnectionDTO contact) {this.contact = contact;}

	public List<MessageDTO> getMessages() {return messages;}

	public void setMessages(List<MessageDTO> messages) {this.messages = messages;}

	public MessageDTO getLastMessage() {return lastMessage;}

	public void setLastMessage(MessageDTO lastMessage) {this.lastMessage = lastMessage;}

	public String getToken() {return token;}

	public void setToken(String token) {this.token = token;}
}
