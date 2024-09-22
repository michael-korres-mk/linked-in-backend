package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

public class ChatDTO extends BasicEntityDTO {
	protected UserBasicDTO creator;
	protected UserBasicDTO recipient;
	protected MessageDTO lastMessage;

	public MessageDTO getLastMessage() {return lastMessage;}

	public void setLastMessage(MessageDTO lastMessage) {this.lastMessage = lastMessage;}

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}

	public UserBasicDTO getRecipient() {return recipient;}

	public void setRecipient(UserBasicDTO recipient) {this.recipient = recipient;}
}
