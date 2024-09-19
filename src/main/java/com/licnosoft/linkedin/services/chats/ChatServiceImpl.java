package com.licnosoft.linkedin.services.chats;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.security.JwtUtil;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

	private final ChatRepository entityRepository;
	private final MessageRepository messageRepository;
	private final UserService userService;
	private final JwtUtil jwtUtil;
	private final ModelMapper modelMapper;

	public ChatServiceImpl(ChatRepository entityRepository, MessageRepository messageRepository, UserService userService, JwtUtil jwtUtil, ModelMapper modelMapper) {
		this.entityRepository = entityRepository;
		this.messageRepository = messageRepository;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ChatDTO> getChats(Long recipientId) {
//		LocalDateTime startDate = DateUtil.toStartOfDay(LocalDate.now().minusDays(Constants.CHAT_RECENCY_DAYS));
		Long userId = userService.getLoggedUser().getId();
		return entityRepository.getChats(userId,recipientId).stream().map(c -> modelMapper.map(c,ChatDTO.class)).toList();
	}


	@Override
	public List<MessageDTO> getChatMessages(Long recipientId) {
		Long userId = userService.getLoggedUser().getId();
		return entityRepository.getChats(userId,recipientId).getFirst().getMessages()
				.stream()
				.map(m -> modelMapper.map(m,MessageDTO.class))
				.toList();
	}

	private Chat getEntityByRecipientId(Long recipientId){
		Long creatorId = userService.getLoggedUser().getId();
		List<Chat> chats = entityRepository.getChats(creatorId,recipientId);
		return (chats != null && !chats.isEmpty())? chats.getFirst() : null;
	}

	@Override
	public Chat findEntityById(Long id){
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε συνομιλία με το συγκεκριμένο id:" + id + "."));
	}

	@Override
	public ChatDTO getOrCreateChat(Long recipientId) {
		Chat entity = getEntityByRecipientId(recipientId);
		if (entity != null) return modelMapper.map(entity, ChatDTO.class);

		User loggedUser = this.userService.getLoggedUser();
		User recipient = this.userService.findEntityById(recipientId);

		Chat newEntity = new Chat();

		newEntity.setCreator(loggedUser);
		newEntity.setCreationDate(LocalDateTime.now());
		newEntity.setRecipient(recipient);

		return modelMapper.map(save(newEntity), ChatDTO.class);

	}

	@Override
	public Chat save(Chat entity) {
		return entityRepository.save(entity);
	}

	@Override
	public Message saveMessage(Message entity) {
		return messageRepository.save(entity);
	}

	@Override
	public MessageDTO saveMessage(MessageDTO dto) {
		Message temp = modelMapper.map(dto, Message.class);

		Message message =  saveMessage(temp);

		Chat entity = findEntityById(message.getChat().getId());

		entity.setLastMessage(message);

		Long senderId = jwtUtil.decodeJwtToUserInfo(dto.getToken()).getId();

		User sender = userService.findEntityById(senderId);

		if (sender == null) throw new AppNotFoundException("Δε βρέθηκε χρήστης (αποστολέας) με το συγκεκριμένο id: " + senderId +".");

		message.setSender(sender);
		message.setCreationTimestamp(LocalDateTime.now());

		save(entity);

		return modelMapper.map(message,MessageDTO.class);

	}

}
