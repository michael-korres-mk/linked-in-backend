package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;

public interface ConnectionRequestService {

	UserConnectionDTO findRequests(Long recipientId,Boolean accepted);

	UserConnectionDTO findById(Long id);

	UserConnection findEntityById(Long id);

	UserConnectionDTO save(UserConnectionDTO dto, boolean insert);

	UserConnection save(UserConnection entity);

	void deleteById(Long id);

}
