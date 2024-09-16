package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.config.LocalDateTimeAdapter;
import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class UserConnectionDTO extends EntityDTO {
	protected PlainEntityDTO user;
	protected PlainEntityDTO recipient;
	protected LocalDateTime creationDate;
	protected LocalDateTime connectionDate;

	public PlainEntityDTO getUser() {return user;}

	public void setUser(PlainEntityDTO user) {this.user = user;}

	public PlainEntityDTO getRecipient() {return recipient;}

	public void setRecipient(PlainEntityDTO recipient) {this.recipient = recipient;}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlElement(name = "creation-date")
	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlElement(name = "connection-date")
	public LocalDateTime getConnectionDate() {return connectionDate;}

	public void setConnectionDate(LocalDateTime connectionDate) {this.connectionDate = connectionDate;}
}
