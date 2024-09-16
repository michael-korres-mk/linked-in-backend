package com.licnosoft.linkedin.repository.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConnectionRequestRepository extends JpaRepository<UserConnection, Long> {
	Optional<UserConnection> findByCreator_IdAndRecipient_Id(Long id, Long recipientId);

}
