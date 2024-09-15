package com.licnosoft.linkedin.util;

import com.licnosoft.linkedin.model.users.UserBasicDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
public class UserListWrapper {

	private List<UserBasicDTO> users;

	public UserListWrapper() {}

	public UserListWrapper(List<UserBasicDTO> users) {
		this.users = users;
	}

	@XmlElement(name = "user")
	public List<UserBasicDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserBasicDTO> users) {
		this.users = users;
	}
}
