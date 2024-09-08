package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.domain.network.ConnectionRequest;
import com.licnosoft.linkedin.model.network.ConnectionRequestDTO;

public interface ConnectionRequestService {
	ConnectionRequestDTO findById(Long id);

	ConnectionRequest findEntityById(Long id);


	ConnectionRequestDTO save(ConnectionRequestDTO dto, boolean insert);

	ConnectionRequest save(ConnectionRequest entity);

	void deleteById(Long id);

}
