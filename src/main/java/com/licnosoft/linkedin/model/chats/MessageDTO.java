package com.licnosoft.linkedin.model.chats;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class MessageDTO extends EntityDTO {
	private PlainEntityDTO sender;
	private PlainEntityDTO recipient;
	private LocalDateTime creationDate;
	private String message;
	private boolean delivered;
	private boolean read;

	public PlainEntityDTO getSender() {return sender;}

	public void setSender(PlainEntityDTO sender) {this.sender = sender;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	public String getMessage() {return message;}

	public void setMessage(String message) {this.message = message;}

	public boolean isDelivered() {return delivered;}

	public void setDelivered(boolean delivered) {this.delivered = delivered;}

	public boolean isRead() {return read;}

	public void setRead(boolean read) {this.read = read;}
}
