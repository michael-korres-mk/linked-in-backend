package com.licnosoft.linkedin.services.posts;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.posts.Post;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.PostDetailedDTO;
import com.licnosoft.linkedin.repository.posts.PostRepository;
import com.licnosoft.linkedin.services.general.FileService;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServiceImpl implements PostService{

	private final PostRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final FileService fileService;

	public PostServiceImpl(PostRepository entityRepository, ModelMapper modelMapper, UserService userService, FileService fileService) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.fileService = fileService;
	}

	@Override
	public Page<PostDetailedDTO> search(String searchTerm, Boolean enabled, Pageable pageable) {
		return entityRepository.search(searchTerm, enabled, pageable)
				.map(entity -> modelMapper.map(entity, PostDetailedDTO.class));
	}

	@Override
	public PostDetailedDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), PostDetailedDTO.class);
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


//	@Override
//	public PostBasicDTO save(PostBasicDTO dto, boolean insert) {
//		if (!Utilities.validateEntityDto(dto, insert)) {
//			throw new AppValidationException();
//		}
//
//		Post entity = modelMapper.map(dto, Post.class);
//
//		if(insert){
//			User creator = userService.getLoggedUser();
//			entity.setCreator(creator);
//			entity.setCreationDate(LocalDateTime.now());
//		}
//
//		return modelMapper.map(save(entity), PostBasicDTO.class);
//	}

	@Override
	public PostDetailedDTO save(PostDetailedDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) {
			throw new AppValidationException();
		}
		User creator = userService.getLoggedUser();
		Post entity = modelMapper.map(dto, Post.class);
		if (insert) {
			entity.setCreator(creator);
			entity.setCreationDate(LocalDateTime.now());
			entity.setEnabled(true);
		}
		entity.getPhotos().forEach(postPhoto -> {
			if (insert) {
				postPhoto.setId(null);
			}
			fileService.updateFilePost(postPhoto.getFile().getId(), entity.getId());
			postPhoto.setPost(entity);
		});

		PostDetailedDTO postDetailedDTO = modelMapper.map(save(entity), PostDetailedDTO.class);

//		NotificationKind kind = (insert)?NotificationKind.ANNOUNCEMENT_CREATED:NotificationKind.ANNOUNCEMENT_UPDATED;
//		notificationService.addInAppEmailNotificationToProviders(kind, entity.getTitle(), entity.getDescription(),entity.getMarket(),"/announcements/" + entity.getId() + "?view=1");

		return postDetailedDTO;

	}

	@Override
	public Post save(Post entity) {
		if (!validateFields(entity)) {
			throw new AppValidationException();
		}
		entity.setModificationDate(LocalDateTime.now());

		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	private boolean validateFields(Post entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}

}
