package com.licnosoft.linkedin.repository.chats;

import com.licnosoft.linkedin.domain.chats.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

//    @Query("SELECT c FROM Chat c WHERE c.connection.creator.id = :userId AND c.lastMessage.creationDate >= :startDate")
//    List<Chat> getRecentChats(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);

        @Query("""
              SELECT c FROM Chat c WHERE (:creatorId IS NULL OR c.recipient.id = :creatorId OR c.creator.id = :creatorId)
              AND (:recipientId IS NULL OR c.recipient.id = :recipientId OR c.creator.id = :recipientId)
        """)
        List<Chat> getChats(@Param("creatorId") Long creatorId,@Param("recipientId") Long recipientId);
//    TODO: Fix chat sorting ORDER BY CASE WHEN c.lastMessage IS NULL THEN 1 ELSE 0 END ASC, c.lastMessage.creationTimestamp ASC
}
