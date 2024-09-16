package com.licnosoft.linkedin.repository.posts;

import com.licnosoft.linkedin.domain.posts.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("""
		SELECT p FROM Post p
		WHERE p.creator.id = :creatorId
		OR p.creator.id IN (SELECT c.recipient.id FROM UserConnection c WHERE (c.creator.id = :creatorId OR c.recipient.id = :creatorId) AND c.connectionDate IS NOT null)
		OR EXISTS (SELECT 1 FROM p.likes l WHERE l.creator.id IN (SELECT c.recipient.id FROM UserConnection c WHERE c.creator.id = :creatorId AND c.connectionDate IS NOT null))
		ORDER BY p.creationDate ASC
    """)
	Page<Post> search(@Param("creatorId") Long creatorId, Pageable pageable);
}
