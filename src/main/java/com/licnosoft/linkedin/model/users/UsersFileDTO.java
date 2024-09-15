package com.licnosoft.linkedin.model.users;

import java.util.List;

public class UsersFileDTO {
	protected List<UserBasicDTO> users;
	protected Boolean xml;

	public List<UserBasicDTO> getUsers() {return users;}

	public void setUsers(List<UserBasicDTO> users) {this.users = users;}

	public boolean isXml() {return xml;}

	public void setXml(boolean xml) {this.xml = xml;}
}
