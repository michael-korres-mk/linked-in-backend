package com.licnosoft.linkedin.model.users;

import java.util.List;

public class UserDetailedDTO extends UserBasicDTO {

	protected List<UserConnectionDTO> connections;

	public List<UserConnectionDTO> getConnections() {return connections;}

	public void setConnections(List<UserConnectionDTO> connections) {this.connections = connections;}
}
