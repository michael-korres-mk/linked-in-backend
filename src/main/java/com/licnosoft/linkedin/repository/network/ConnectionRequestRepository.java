package com.licnosoft.linkedin.repository.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConnectionRequestRepository extends JpaRepository<UserConnection, Long> {

	@Query("""
		SELECT uc FROM UserConnection uc
		WHERE (:creatorId IS NULL OR uc.recipient.id = :creatorId OR uc.creator.id = :creatorId)
		AND (:recipientId IS NULL OR uc.recipient.id = :recipientId OR uc.creator.id = :recipientId)
		AND (:accepted IS NULL OR (:accepted = true AND uc.connectionDate IS NOT NULL) OR (:accepted = false AND uc.connectionDate IS NULL))
		"""
	 )
	Page<UserConnection> findByCreatorAndRecipient(Long creatorId, Long recipientId, Boolean accepted, Pageable pageable);
}
