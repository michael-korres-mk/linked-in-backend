package com.licnosoft.linkedin.services.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.domain.chats.Message;
import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.model.chats.MessageDTO;

import java.util.List;

public interface ChatService {
	List<ChatDTO> getChats();
	List<MessageDTO> getChatMessages(Long recipientId);
	Chat findEntityById(Long id);
	ChatDTO save(ChatDTO dto);
	Chat save(Chat entity);
	MessageDTO saveMessage(MessageDTO dto);
	Message saveMessage(Message entity);
}
