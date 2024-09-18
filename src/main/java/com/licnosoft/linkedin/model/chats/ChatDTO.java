package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

public class ChatDTO extends BasicEntityDTO {
	protected PlainEntityDTO creator;
	protected PlainEntityDTO recipient;
	protected MessageDTO lastMessage;

	public MessageDTO getLastMessage() {return lastMessage;}

	public void setLastMessage(MessageDTO lastMessage) {this.lastMessage = lastMessage;}

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}
}
