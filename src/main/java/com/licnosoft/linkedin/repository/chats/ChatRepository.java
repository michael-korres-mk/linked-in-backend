package com.licnosoft.linkedin.repository.chats;

import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.domain.Chat;
import com.licnosoft.linkedin.domain.notifications.NotificationRecipient;
import com.licnosoft.linkedin.model.chats.ChatDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    // TODO: where last message creation date >= startDate
    @Query("""
            SELECT c FROM Chat c
            """)
    List<NotificationRecipient> getRecentChats(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);

    ChatDTO getChatByContact(User contact);
}
