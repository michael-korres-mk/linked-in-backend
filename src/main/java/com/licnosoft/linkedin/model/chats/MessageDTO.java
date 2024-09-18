package com.licnosoft.linkedin.model.chats;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class MessageDTO extends EntityDTO {
	protected PlainEntityDTO sender;
	protected PlainEntityDTO recipient;
	protected EntityDTO chat;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	protected LocalDateTime creationTimestamp;
	protected String text;
	protected boolean delivered;
	protected boolean read;

	protected String token;

	public PlainEntityDTO getSender() {return sender;}

	public void setSender(PlainEntityDTO sender) {this.sender = sender;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}

	public String getText() {return text;}

	public void setText(String text) {this.text = text;}

	public boolean isDelivered() {return delivered;}

	public void setDelivered(boolean delivered) {this.delivered = delivered;}

	public boolean isRead() {return read;}

	public void setRead(boolean read) {this.read = read;}

	public EntityDTO getChat() {return chat;}

	public void setChat(EntityDTO chat) {this.chat = chat;}

	public String getToken() {return token;}

	public void setToken(String token) {this.token = token;}

	public LocalDateTime getCreationTimestamp() {return creationTimestamp;}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {this.creationTimestamp = creationTimestamp;}
}
