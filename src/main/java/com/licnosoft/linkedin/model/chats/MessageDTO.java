package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class MessageDTO extends EntityDTO {
	protected PlainEntityDTO sender;
	protected PlainEntityDTO recipient;
	protected EntityDTO chat;
	protected LocalDateTime creationDate;
	protected String text;
	protected boolean delivered;
	protected boolean read;

	public PlainEntityDTO getSender() {return sender;}

	public void setSender(PlainEntityDTO sender) {this.sender = sender;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public String getText() {return text;}

	public void setText(String text) {this.text = text;}

	public boolean isDelivered() {return delivered;}

	public void setDelivered(boolean delivered) {this.delivered = delivered;}

	public boolean isRead() {return read;}

	public void setRead(boolean read) {this.read = read;}

	public EntityDTO getChat() {return chat;}

	public void setChat(EntityDTO chat) {this.chat = chat;}
}
