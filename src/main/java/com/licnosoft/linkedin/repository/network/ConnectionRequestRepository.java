package com.licnosoft.linkedin.repository.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConnectionRequestRepository extends JpaRepository<UserConnection, Long> {

	@Query("""
        SELECT uc FROM UserConnection uc
        WHERE ((uc.recipient.id = :recipientId AND uc.creator.id = :creatorId) OR (uc.creator.id = :recipientId AND uc.recipient.id = :creatorId))
		AND (:accepted IS NULL OR (:accepted = true AND uc.connectionDate IS NOT NULL) OR (:accepted = false AND uc.connectionDate IS NULL))
	 """
	 )
	UserConnection findByCreatorAndRecipient(Long creatorId, Long recipientId,Boolean accepted);
}
