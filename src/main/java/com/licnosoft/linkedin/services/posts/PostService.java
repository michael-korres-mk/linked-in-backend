package com.licnosoft.linkedin.services.posts;

import com.licnosoft.linkedin.domain.posts.Post;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.CommentDTO;
import com.licnosoft.linkedin.model.posts.PostDetailedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
	Page<PostDetailedDTO> search(String searchTerm, Boolean enabled, Pageable pageable);

	PostDetailedDTO findById(Long id);

	Post findEntityById(Long id);

	ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto);

	PostDetailedDTO save(PostDetailedDTO dto, boolean insert);

	Post save(Post entity);

	void deleteById(Long id);

	ToggleEntityDTO toggleLike(ToggleEntityDTO dto);

	CommentDTO comment(CommentDTO dto);
}
