package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.domain.users.UserConnection;

import java.util.List;

public class UserDetailedDTO extends UserBasicDTO {

	protected List<UserConnection> connections;

	public List<UserConnection> getConnections() {return connections;}

	public void setConnections(List<UserConnection> connections) {this.connections = connections;}
}
