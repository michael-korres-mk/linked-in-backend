package com.licnosoft.linkedin.model.network;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class UserConnectionDTO extends BasicEntityDTO {

	protected PlainEntityDTO creator;
	protected PlainEntityDTO recipient;
	protected Long recipientId;
	protected LocalDateTime connectionDate;

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}

	public Long getRecipientId() {return recipientId;}

	public void setRecipientId(Long recipientId) {this.recipientId = recipientId;}

	public LocalDateTime getConnectionDate() {return connectionDate;}

	public void setConnectionDate(LocalDateTime connectionDate) {this.connectionDate = connectionDate;}
}
