package com.licnosoft.linkedin.repository.network;

import com.licnosoft.linkedin.domain.network.UserConnection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConnectionRequestRepository extends JpaRepository<UserConnection, Long> {


	/*  Query Documentation
	   1. creatorId = null => Fetch all requests (admin)
	   2. creatorId != null AND recipientId = null => Fetch requests where user is creator OR recipient
	   3. creatorId != null AND recipientId != null => Fetch requests where (user is creator AND recipient is recipient) OR the opposite
	   4. accepted : true or false => Fetch only accepted requests (connections) or only not accepted base on connection date's existence
   */
	@Query("""
		SELECT uc FROM UserConnection uc
		WHERE ((:creatorId IS NULL)
		OR ((uc.creator.id = :creatorId OR  uc.recipient.id = :creatorId) AND :recipientId IS NULL)
		OR ((uc.creator.id = :creatorId AND uc.recipient.id =:recipientId) OR (uc.creator.id = :recipientId AND uc.recipient.id =:creatorId)))
		AND (:accepted IS NULL OR (:accepted = true AND uc.connectionDate IS NOT NULL) OR (:accepted = false AND uc.connectionDate IS NULL))
	""")
	Page<UserConnection> findByCreatorAndRecipient(Long creatorId, Long recipientId, Boolean accepted, Pageable pageable);

	@Query("""
		SELECT uc FROM UserConnection uc
		WHERE ((:creatorId IS NULL)
		OR ((uc.creator.id = :creatorId OR  uc.recipient.id = :creatorId) AND :recipientId IS NULL)
		OR ((uc.creator.id = :creatorId AND uc.recipient.id =:recipientId) OR (uc.creator.id = :recipientId AND uc.recipient.id =:creatorId)))
		AND (:accepted IS NULL OR (:accepted = true AND uc.connectionDate IS NOT NULL) OR (:accepted = false AND uc.connectionDate IS NULL))
	""")
	List<UserConnection> findByCreatorAndRecipient(Long creatorId, Long recipientId, Boolean accepted);

}
