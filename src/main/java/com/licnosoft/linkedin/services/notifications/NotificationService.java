package com.licnosoft.linkedin.services.notifications;

import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.notifications.NotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getFreshNotifications();

    Page<NotificationDTO> findUserNotifications(String searchTerm, NotificationKind kind, LocalDateTime createdFrom, LocalDateTime createdTo, Pageable pageable);

    void dismiss(Long id);

    void addNotification(NotificationKind kind, String title, List<User> users, String message, LocalDateTime endDate, String url);

}
