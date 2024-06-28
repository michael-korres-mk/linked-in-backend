package com.licnosoft.linkedin.services.posts;

import com.licnosoft.linkedin.domain.posts.Post;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
	Page<PostDTO> search(String searchTerm, Boolean enabled, Pageable pageable);

	PostDTO findById(Long id);

	Post findEntityById(Long id);

	ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto);

	PostDTO save(PostDTO dto, boolean insert);

	Post save(Post entity);

	void deleteById(Long id);

}
