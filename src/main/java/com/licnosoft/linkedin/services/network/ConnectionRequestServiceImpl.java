package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.network.ConnectionRequest;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.network.ConnectionRequestDTO;
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
	public ConnectionRequestDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), ConnectionRequestDTO.class);
	}

	@Override
	public ConnectionRequest findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(AppNotFoundException::new);
	}

	@Override
	public ConnectionRequestDTO save(ConnectionRequestDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) throw new AppValidationException();

		User creator = userService.getLoggedUser();
		ConnectionRequest entity = modelMapper.map(dto, ConnectionRequest.class);
		if (insert) {
			entity.setCreator(creator);
			entity.setRecipient(userService.findEntityById(dto.getRecipientId()));
		}

		ConnectionRequestDTO connectionRequestDTO = modelMapper.map(save(entity), ConnectionRequestDTO.class);

//		NotificationKind kind = (insert)?NotificationKind.ANNOUNCEMENT_CREATED:NotificationKind.ANNOUNCEMENT_UPDATED;
//		notificationService.addInAppEmailNotificationToProviders(kind, entity.getTitle(), entity.getDescription(),entity.getMarket(),"/announcements/" + entity.getId() + "?view=1");

		return connectionRequestDTO;

	}

	@Override
	public ConnectionRequest save(ConnectionRequest entity) {
		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	private boolean validateFields(ConnectionRequest entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}
}
