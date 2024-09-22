package com.licnosoft.linkedin.model.chats;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

import java.time.LocalDateTime;

public class MessageDTO extends EntityDTO {
	protected UserBasicDTO sender;
	protected UserBasicDTO recipient;
	protected EntityDTO chat;
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	protected LocalDateTime creationTimestamp;
	protected String text;
	protected boolean delivered;
	protected boolean read;

	protected String token;

	public UserBasicDTO getSender() {return sender;}

	public void setSender(UserBasicDTO sender) {this.sender = sender;}

	public UserBasicDTO getRecipient() {return recipient;}

	public void setRecipient(UserBasicDTO recipient) {this.recipient = recipient;}

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
