package com.licnosoft.linkedin.repository.network;

import com.licnosoft.linkedin.domain.network.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
	Optional<ConnectionRequest> findByCreator_IdAndRecipient_Id(Long id, Long recipientId);

}
