package com.licnosoft.linkedin.controllers.chats;

import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.model.chats.MessageDTO;
import com.licnosoft.linkedin.services.chats.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService entityService;

    public ChatController(SimpMessagingTemplate messagingTemplate, ChatService entityService) {
        this.messagingTemplate = messagingTemplate;
        this.entityService = entityService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ChatDTO>> getChats() {
        return new ResponseEntity<>(entityService.getChats(), HttpStatus.OK);
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<List<MessageDTO>> getChatMessages(@PathVariable("id") Long recipientId) {
        return new ResponseEntity<>(entityService.getChatMessages(recipientId), HttpStatus.OK);
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload MessageDTO dto) {
        String userId = dto.getRecipient().getId().toString();
        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", entityService.saveMessage(dto));
    }
}
