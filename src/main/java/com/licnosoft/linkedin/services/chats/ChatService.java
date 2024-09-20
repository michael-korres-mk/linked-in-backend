package com.licnosoft.linkedin.services.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.domain.chats.Message;
import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.model.chats.MessageDTO;

import java.util.List;

public interface ChatService {
	List<ChatDTO> getChats(Long recipientId);
	List<MessageDTO> getChatMessages(Long recipientId);
	Chat findEntityById(Long id);
	Chat save(Chat entity);
	MessageDTO saveMessage(MessageDTO dto);
	Message saveMessage(Message entity);

	ChatDTO getOrCreateChat(Long recipientId);
}
