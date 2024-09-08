package com.licnosoft.linkedin.model.network;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

public class ConnectionRequestDTO extends BasicEntityDTO {

	protected PlainEntityDTO creator;
	protected Long recipientId;
	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public Long getRecipientId() {return recipientId;}

	public void setRecipientId(Long recipientId) {this.recipientId = recipientId;}
}
