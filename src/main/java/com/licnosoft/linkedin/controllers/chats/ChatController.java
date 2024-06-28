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

    @GetMapping("/recent/list")
    public ResponseEntity<List<ChatDTO>> getRecentChats() {
        return new ResponseEntity<>(entityService.getRecentChats(), HttpStatus.OK);
    }

    @GetMapping("/history/{contactId}")
    public ResponseEntity<ChatDTO> getChatHistory(@PathVariable Long contactId) {
        return new ResponseEntity<>(entityService.getChatHistory(contactId), HttpStatus.OK);
    }

    @MessageMapping("/chat")
//    @PreAuthorize("hasAuthority('chat_edit')")
    // TODO: Fix Message saving in db - refactor chat entities
    public void processMessage(@Payload MessageDTO dto) {

        String userId = dto.getRecipient().getId().toString();
//        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", entityService.save(dto));
    }
}
