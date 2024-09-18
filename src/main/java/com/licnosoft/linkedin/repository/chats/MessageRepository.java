package com.licnosoft.linkedin.repository.chats;

import com.licnosoft.linkedin.domain.chats.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {}
