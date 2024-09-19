package com.licnosoft.linkedin.model.network;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

import java.time.LocalDateTime;

public class UserConnectionDTO extends BasicEntityDTO {

	protected UserBasicDTO creator;
	protected UserBasicDTO recipient;
	protected Long recipientId;
	protected LocalDateTime connectionDate;

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}

	public UserBasicDTO getRecipient() {return recipient;}

	public void setRecipient(UserBasicDTO recipient) {this.recipient = recipient;}

	public Long getRecipientId() {return recipientId;}

	public void setRecipientId(Long recipientId) {this.recipientId = recipientId;}

	public LocalDateTime getConnectionDate() {return connectionDate;}

	public void setConnectionDate(LocalDateTime connectionDate) {this.connectionDate = connectionDate;}
}
