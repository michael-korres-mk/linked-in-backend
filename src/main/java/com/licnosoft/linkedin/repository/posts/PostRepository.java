package com.licnosoft.linkedin.repository.posts;

import com.licnosoft.linkedin.domain.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
