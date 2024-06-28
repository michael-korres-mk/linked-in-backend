package com.licnosoft.linkedin.repository.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query("SELECT c FROM Chat c WHERE c.contact.user.id = :userId AND c.lastMessage.creationDate >= :startDate")
    List<Chat> getRecentChats(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);

    @Query("SELECT c FROM Chat c WHERE c.contact.user.id = :userId AND c.contact.contact.id = :contactId")
    Chat getChatByContact(@Param("userId") Long userId,@Param("contactId") Long contactId);
}
