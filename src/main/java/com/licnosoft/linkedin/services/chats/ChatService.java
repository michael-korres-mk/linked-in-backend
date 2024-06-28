package com.licnosoft.linkedin.services.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import com.licnosoft.linkedin.model.chats.ChatDTO;

import java.util.List;

public interface ChatService {
	List<ChatDTO> getRecentChats();

	ChatDTO getChatHistory(Long contactId);

	ChatDTO save(ChatDTO dto);

	Chat save(Chat entity);
}
