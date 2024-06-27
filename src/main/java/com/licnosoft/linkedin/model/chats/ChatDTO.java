package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.users.UserContactDTO;
import java.util.List;

public class ChatDTO extends EntityDTO {

	private UserContactDTO contact;
	private List<MessageDTO> messages;

	public UserContactDTO getContact() {return contact;}

	public void setContact(UserContactDTO contact) {this.contact = contact;}

	public List<MessageDTO> getMessages() {return messages;}

	public void setMessages(List<MessageDTO> messages) {this.messages = messages;}
}
