package com.licnosoft.linkedin.controllers.chats;

import com.licnosoft.linkedin.model.chats.ChatDTO;
import com.licnosoft.linkedin.services.chats.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    private final ChatService entityService;

    @Autowired
    public ChatController(ChatService entityService) {
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

//    @GetMapping("/search")
//    public ResponseEntity<Page<ChatDTO>> findAllPaged(
//            @RequestParam(value = "q", required = false) String searchTerm,
//            @RequestParam(value = "chatkind", required = false) ChatKind kind,
//            @RequestParam(value = "createdfrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdFrom,
//            @RequestParam(value = "createdto", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdTo, Pageable pageable) {
//
//        return new ResponseEntity<>(entityService.findUserChats(searchTerm, kind, DateUtil.toStartOfDay(createdFrom), DateUtil.toEndOfDay(createdTo), pageable), HttpStatus.OK);
//    }

//    @PutMapping("/dismiss/{id}")
//    public ResponseEntity<Void> saveDismissed(@PathVariable Long id) {
//        entityService.dismiss(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
