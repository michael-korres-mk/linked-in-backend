package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;

import java.util.List;

public class ChatDTO extends EntityDTO {

	private UserConnectionDTO connection;
	private List<MessageDTO> messages;

	private MessageDTO lastMessage;

	private String token;

	public UserConnectionDTO getConnection() {return connection;}

	public void setConnection(UserConnectionDTO connection) {this.connection = connection;}

	public List<MessageDTO> getMessages() {return messages;}

	public void setMessages(List<MessageDTO> messages) {this.messages = messages;}

	public MessageDTO getLastMessage() {return lastMessage;}

	public void setLastMessage(MessageDTO lastMessage) {this.lastMessage = lastMessage;}

	public String getToken() {return token;}

	public void setToken(String token) {this.token = token;}
}
