package com.licnosoft.linkedin.controllers.notifications;


import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.model.notifications.NotificationDTO;
import com.licnosoft.linkedin.services.notifications.NotificationService;
import com.licnosoft.linkedin.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService entityService;

    @Autowired
    public NotificationController(NotificationService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/fresh/list")
    public ResponseEntity<List<NotificationDTO>> getFreshNotifications() {
        return new ResponseEntity<>(entityService.getFreshNotifications(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<NotificationDTO>> findAllPaged(
            @RequestParam(value = "q", required = false) String searchTerm,
            @RequestParam(value = "notificationkind", required = false) NotificationKind kind,
            @RequestParam(value = "createdfrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdFrom,
            @RequestParam(value = "createdto", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdTo, Pageable pageable) {

        return new ResponseEntity<>(entityService.findUserNotifications(searchTerm, kind, DateUtil.toStartOfDay(createdFrom), DateUtil.toEndOfDay(createdTo), pageable), HttpStatus.OK);
    }

    @PutMapping("/dismiss/{id}")
    public ResponseEntity<Void> saveDismissed(@PathVariable Long id) {
        entityService.dismiss(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
