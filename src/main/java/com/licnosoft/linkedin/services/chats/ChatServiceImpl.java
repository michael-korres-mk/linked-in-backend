package com.licnosoft.linkedin.services.chats;

import com.auth0.jwt.JWT;
import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.repository.chats.ChatRepository;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	private final ChatRepository entityRepository;

	private final UserService userService;
	private final ModelMapper modelMapper;

	public ChatServiceImpl(ChatRepository entityRepository, UserService userService, ModelMapper modelMapper) {
		this.entityRepository = entityRepository;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ChatDTO> getRecentChats() {
		LocalDateTime startDate = DateUtil.toStartOfDay(LocalDate.now().minusDays(Constants.CHAT_RECENCY_DAYS));
		return entityRepository.getRecentChats(userService.getLoggedUser().getId(),startDate).stream().map(c -> modelMapper.map(c,ChatDTO.class)).toList();
	}

	@Override
	public ChatDTO getChatHistory(Long recipientId) {
		var userId = userService.getLoggedUser().getId();
		return modelMapper.map(entityRepository.getChatByRecipient(userId,recipientId),ChatDTO.class);
	}

	@Override
	public ChatDTO save(ChatDTO dto) {
//		if (!(validateDto(dto))) {
//			throw new AppValidationException();
//		}
		Chat entity = modelMapper.map(dto, Chat.class);
		JWT parsedJwt;
		User sender;
//		try {
////			parsedJwt = JWTParser.parse(dto.getToken());
////			sender = userService.findByAfm(parsedJwt.getJWTClaimsSet().getStringClaim(afmClaim));
//		} catch (ParseException e) {
//			throw new AppNotFoundException();
//		}
//		if (sender == null) {
//			throw new AppNotFoundException();
//		}
//		entity.se(sender);
//		entity.setCreationDate(LocalDateTime.now());



		return modelMapper.map(save(entity), ChatDTO.class);
	}

	@Override
	public Chat save(Chat entity) {
//		if (!validateFields(entity)) {
//			throw new AppValidationException();
//		}
		return entityRepository.save(entity);
	}

	private boolean validateDto(ChatDTO dto) {
		return dto.getLastMessage() != null && dto.getConnection() != null && dto.getConnection().getRecipient() != null && dto.getConnection().getRecipient().getId() != null;
	}

//	private boolean validateFields(UserChat entity) {
//		return entity.getMessage() != null && entity.getCreationDate() != null && entity.getRecipient() != null;
//	}
}
