package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.network.UserConnection;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;
import com.licnosoft.linkedin.repository.network.ConnectionRequestRepository;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConnectionRequestServiceImpl implements ConnectionRequestService{
	private final ConnectionRequestRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;

	public ConnectionRequestServiceImpl(ConnectionRequestRepository entityRepository, ModelMapper modelMapper, UserService userService) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
	}

	@Override
	public UserConnectionDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), UserConnectionDTO.class);
	}

	@Override
	public UserConnection findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αίτημα σύνδεσης με το συγκεκριμένο id:" + id + "."));
	}

	@Override
	public UserConnectionDTO save(UserConnectionDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) throw new AppValidationException();

		User creator = userService.getLoggedUser();
		UserConnection entity = modelMapper.map(dto, UserConnection.class);
		if (insert) {
			entity.setCreator(creator);
			entity.setRecipient(userService.findEntityById(dto.getRecipientId()));
		}

		UserConnectionDTO userConnectionDTO = modelMapper.map(save(entity), UserConnectionDTO.class);

//		NotificationKind kind = (insert)?NotificationKind.ANNOUNCEMENT_CREATED:NotificationKind.ANNOUNCEMENT_UPDATED;
//		notificationService.addInAppEmailNotificationToProviders(kind, entity.getTitle(), entity.getDescription(),entity.getMarket(),"/announcements/" + entity.getId() + "?view=1");

		return userConnectionDTO;

	}

	@Override
	public UserConnection save(UserConnection entity) {
		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	private boolean validateFields(UserConnection entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}
}
