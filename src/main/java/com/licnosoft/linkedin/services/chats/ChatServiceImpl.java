package com.licnosoft.linkedin.services.chats;

import com.auth0.jwt.JWT;
import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.domain.chats.Message;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.model.chats.MessageDTO;
import com.licnosoft.linkedin.repository.chats.ChatRepository;
import com.licnosoft.linkedin.repository.chats.MessageRepository;
import com.licnosoft.linkedin.services.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	private final ChatRepository entityRepository;
	private final MessageRepository messageRepository;
	private final UserService userService;
	private final ModelMapper modelMapper;

	public ChatServiceImpl(ChatRepository entityRepository, MessageRepository messageRepository, UserService userService, ModelMapper modelMapper) {
		this.entityRepository = entityRepository;
		this.messageRepository = messageRepository;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ChatDTO> getChats() {
//		LocalDateTime startDate = DateUtil.toStartOfDay(LocalDate.now().minusDays(Constants.CHAT_RECENCY_DAYS));
		return entityRepository.getChats(userService.getLoggedUser().getId()).stream().map(c -> modelMapper.map(c,ChatDTO.class)).toList();
	}

	@Override
	public List<MessageDTO> getChatMessages(Long recipientId) {
		Long userId = userService.getLoggedUser().getId();
		return entityRepository.getChatByRecipient(userId,recipientId).getMessages()
				.stream()
				.map(m -> modelMapper.map(m,MessageDTO.class))
				.toList();
	}

	@Override
	public Chat findEntityById(Long id){
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε συνομιλία με το συγκεκριμένο id:" + id + "."));
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

	@Override
	public Message saveMessage(Message entity) {
	//		if (!validateFields(entity)) {
	//			throw new AppValidationException();
	//		}
		return messageRepository.save(entity);
	}

	@Override
	public MessageDTO saveMessage(MessageDTO dto) {
		Message temp = modelMapper.map(dto, Message.class);

		Message message =  saveMessage(temp);

		Chat entity = findEntityById(message.getChat().getId());

		entity.setLastMessage(message);

		save(entity);

		return modelMapper.map(message,MessageDTO.class);

	}

//	private boolean validateDto(ChatDTO dto) {
//		return dto.getLastMessage() != null && dto.getRecipient() != null && dto.getRecipient().getId() != null;
//	}

//	private boolean validateFields(UserChat entity) {
//		return entity.getMessage() != null && entity.getCreationDate() != null && entity.getRecipient() != null;
//	}
}
