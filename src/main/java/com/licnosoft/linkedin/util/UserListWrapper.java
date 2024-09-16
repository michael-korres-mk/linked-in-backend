package com.licnosoft.linkedin.util;

import com.licnosoft.linkedin.model.users.UserDetailedDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
public class UserListWrapper {

	private List<UserDetailedDTO> users;

	public UserListWrapper() {}

	public UserListWrapper(List<UserDetailedDTO> users) {
		this.users = users;
	}

	@XmlElement(name = "user")
	public List<UserDetailedDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDetailedDTO> users) {
		this.users = users;
	}
}
