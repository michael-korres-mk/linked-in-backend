package com.licnosoft.linkedin.services.posts;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.posts.Post;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.PostDTO;
import com.licnosoft.linkedin.repository.posts.PostRepository;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService{

	private final PostRepository entityRepository;
	private final ModelMapper modelMapper;

	public PostServiceImpl(PostRepository entityRepository, ModelMapper modelMapper) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Page<PostDTO> search(String searchTerm, Boolean enabled, Pageable pageable) {
//		return entityRepository.search(searchTerm, enabled, pageable)
//				.map(entity -> modelMapper.map(entity, PostDTO.class));
		return null;
	}

	@Override
	public PostDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), PostDTO.class);
	}

	@Override
	public Post findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(AppNotFoundException::new);
	}


	@Override
	public ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto) {
		Post result = findEntityById(dto.getId());
		result.setEnabled(dto.isEnabled());
		return modelMapper.map(save(result), ToggleEntityDTO.class);
	}


	@Override
	public PostDTO save(PostDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) {
			throw new AppValidationException();
		}

		return modelMapper.map(save(modelMapper.map(dto, Post.class)), PostDTO.class);
	}

	@Override
	public Post save(Post entity) {
//		if (!validateFields(entity)) {
//			throw new AppValidationException();
//		}

		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}


}
