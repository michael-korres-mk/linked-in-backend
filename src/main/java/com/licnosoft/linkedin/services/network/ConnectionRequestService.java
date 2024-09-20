package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConnectionRequestService {

	Page<UserConnectionDTO> findRequests(Long creatorId, Long recipientId, Boolean accepted, Pageable pageable);
	List<UserConnectionDTO> findRequests(Long creatorId, Long recipientId, Boolean accepted);

	UserConnectionDTO findById(Long id);

	UserConnection findEntityById(Long id);

	UserConnectionDTO save(UserConnectionDTO dto, boolean insert);

	UserConnection save(UserConnection entity);

	void deleteById(Long id);

}
