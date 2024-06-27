package com.licnosoft.linkedin.services.chats;

import com.licnosoft.linkedin.config.Constants;
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
	public ChatDTO getChatHistory(Long contactId) {
		var userId = userService.getLoggedUser().getId();
		return modelMapper.map(entityRepository.getChatByContact(userId,contactId),ChatDTO.class);
	}
}
