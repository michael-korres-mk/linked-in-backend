package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class UserContactDTO extends EntityDTO {
	protected PlainEntityDTO user;
	protected PlainEntityDTO contact;
	protected LocalDateTime creationDate;

	public PlainEntityDTO getUser() {return user;}

	public void setUser(PlainEntityDTO user) {this.user = user;}

	public PlainEntityDTO getContact() {return contact;}

	public void setContact(PlainEntityDTO contact) {this.contact = contact;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}

}
