package com.licnosoft.linkedin.repository.posts;

import com.licnosoft.linkedin.domain.posts.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
