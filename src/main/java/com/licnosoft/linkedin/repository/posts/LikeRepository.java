package com.licnosoft.linkedin.repository.posts;

import com.licnosoft.linkedin.domain.posts.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

	Optional<Like> findByCreator_Id(Long id);

}
