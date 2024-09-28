package com.licnosoft.linkedin.services.posts;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.general.PostPhoto;
import com.licnosoft.linkedin.domain.posts.Comment;
import com.licnosoft.linkedin.domain.posts.Like;
import com.licnosoft.linkedin.domain.posts.Post;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.CommentDTO;
import com.licnosoft.linkedin.model.posts.PostDetailedDTO;
import com.licnosoft.linkedin.repository.posts.CommentRepository;
import com.licnosoft.linkedin.repository.posts.LikeRepository;
import com.licnosoft.linkedin.repository.posts.PostRepository;
import com.licnosoft.linkedin.services.notifications.NotificationService;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

	private final PostRepository entityRepository;
	private final LikeRepository likeRepository;
	private final CommentRepository commentRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final NotificationService notificationService;

	public PostServiceImpl(PostRepository entityRepository, LikeRepository likeRepository, CommentRepository commentRepository, ModelMapper modelMapper, UserService userService, NotificationService notificationService) {
		this.entityRepository = entityRepository;
		this.likeRepository = likeRepository;
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.notificationService = notificationService;
	}

	@Override
	public Page<PostDetailedDTO> search(Pageable pageable) {
		User user = userService.getLoggedUser();
		return entityRepository.search(user.getId(), pageable)
				.map(entity -> modelMapper.map(entity, PostDetailedDTO.class));
	}

	@Override
	public PostDetailedDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), PostDetailedDTO.class);
	}

	@Override
	public Post findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε άρθρο με το συγκεκριμένο id:" + id + "."));
	}


	@Override
	public ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto) {
		Post result = findEntityById(dto.getId());
		result.setEnabled(dto.isEnabled());
		return modelMapper.map(save(result), ToggleEntityDTO.class);
	}

	@Override
	public PostDetailedDTO save(PostDetailedDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) throw new AppValidationException();

		User creator = userService.getLoggedUser();
		Post entity = modelMapper.map(dto, Post.class);
		Post savedEntity;
		if (insert) {
			entity.setCreator(creator);
			entity.setCreationDate(LocalDateTime.now());
			entity.setEnabled(true);
			List<PostPhoto> photos = entity.getPhotos();
			entity.setPhotos(null);
			savedEntity = save(entity);
			photos.forEach(postPhoto -> postPhoto.setPost(savedEntity));
			savedEntity.setPhotos(photos);
			return modelMapper.map(save(savedEntity), PostDetailedDTO.class);
		}

		List<PostPhoto> photos = entity.getPhotos();
		photos.forEach(postPhoto -> postPhoto.setPost(entity));

		List<Comment> comments = entity.getComments();
		if(comments != null && !comments.isEmpty()) comments.forEach(comment -> comment.setPost(entity));

		List<Like> likes = entity.getLikes();
		if(likes != null && !likes.isEmpty()) likes.forEach(like -> like.setPost(entity));


		return modelMapper.map(save(entity), PostDetailedDTO.class);

	}

	@Override
	public Post save(Post entity) {
		if (!validateFields(entity)) throw new AppValidationException();

		entity.setModificationDate(LocalDateTime.now());

		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	@Override
	public CommentDTO comment(CommentDTO dto){
		User creator = userService.getLoggedUser();
		LocalDateTime creationDate = LocalDateTime.now();
		Comment temp = new Comment(dto.getDescription(),creationDate,creator,findEntityById(dto.getPostId()));

		Comment entity = commentRepository.save(temp);

		notificationService.addNotification(NotificationKind.EXPRESSION_OF_INTEREST,"O χρήστης: " + creator.getTitle() + " σχολίασε: " + entity.getDescription(),entity.getDescription(), null,List.of(entity.getPost().getCreator()),"/posts/" + entity.getPost().getId() + "?view=1",entity.getPost().getId());

		return modelMapper.map(entity,CommentDTO.class);
	}

	@Override
	public ToggleEntityDTO toggleLike(ToggleEntityDTO dto){
		User creator = userService.getLoggedUser();
		LocalDateTime creationDate = LocalDateTime.now();

		Like temp = likeRepository.findByCreator_IdAndPost_Id(creator.getId(),dto.getId()).orElse(new Like());

		if(temp.getId() != null){
			if(dto.isEnabled()) temp.setCreationDate(creationDate);
			else temp.setCreationDate(null);
		}
		else {
			temp.setPost(findEntityById(dto.getId()));
			temp.setCreator(creator);
			temp.setCreationDate(creationDate);
		}

		Like entity = likeRepository.save(temp);

		if(entity.getCreationDate() != null) notificationService.addNotification(NotificationKind.EXPRESSION_OF_INTEREST,"Η δημοσίευσή σας αρέσει στον χρήστη: " + creator.getTitle(),null, null,List.of(entity.getPost().getCreator()),"/posts/" + entity.getPost().getId() + "?view=1",entity.getPost().getId());

		return modelMapper.map(entity,ToggleEntityDTO.class);
	}


	private boolean validateFields(Post entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}

}
