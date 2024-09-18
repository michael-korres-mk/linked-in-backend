package com.licnosoft.linkedin.services.network;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.network.UserConnection;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.network.UserConnectionDTO;
import com.licnosoft.linkedin.repository.network.ConnectionRequestRepository;
import com.licnosoft.linkedin.services.notifications.NotificationService;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConnectionRequestServiceImpl implements ConnectionRequestService{
	private final ConnectionRequestRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final NotificationService notificationService;

	public ConnectionRequestServiceImpl(ConnectionRequestRepository entityRepository, ModelMapper modelMapper, UserService userService, NotificationService notificationService) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.notificationService = notificationService;
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
		UserConnection temp = modelMapper.map(dto, UserConnection.class);
		if (insert) {
			temp.setCreator(creator);
			temp.setRecipient(userService.findEntityById(dto.getRecipientId()));
		}

		UserConnection entity = save(temp);

		notificationService.addNotification(NotificationKind.CONNECTION_REQUEST, "Έχετε νέο αίτημα σύνδεσης: " + creator.getTitle(),null, null,List.of(entity.getRecipient()),"/in/" + creator.getId() + "?view=1");

		return modelMapper.map(entity, UserConnectionDTO.class);
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
