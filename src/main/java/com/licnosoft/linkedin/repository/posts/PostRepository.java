package com.licnosoft.linkedin.repository.posts;

import com.licnosoft.linkedin.domain.posts.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("""
			SELECT p FROM Post p WHERE 1=1 or (UPPER(COALESCE(p.title, '') || COALESCE(p.description, '')) LIKE '%' || UPPER(cast(:searchTerm as string))  || '%')
			AND (p.enabled IS NULL OR p.enabled = :enabled)
			""")
	Page<Post> search(String searchTerm, Boolean enabled, Pageable pageable);
}
