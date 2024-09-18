package com.licnosoft.linkedin.repository.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

//    @Query("SELECT c FROM Chat c WHERE c.connection.creator.id = :userId AND c.lastMessage.creationDate >= :startDate")
//    List<Chat> getRecentChats(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);

        @Query("SELECT c FROM Chat c WHERE c.creator.id = :userId OR c.recipient.id = :userId ORDER BY c.lastMessage.creationDate ASC")
        List<Chat> getChats(@Param("userId") Long userId);


    @Query("SELECT c FROM Chat c WHERE (c.creator.id = :userId AND c.recipient.id = :recipientId) OR (c.creator.id = :recipientId AND c.recipient.id = :userId)")
    Chat getChatByRecipient(@Param("userId") Long userId,@Param("recipientId") Long recipientId);
}
