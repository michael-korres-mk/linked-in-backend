package com.licnosoft.linkedin.services.notifications;

import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.notifications.Notification;
import com.licnosoft.linkedin.domain.notifications.NotificationRecipient;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.notifications.NotificationDTO;
import com.licnosoft.linkedin.repository.notifications.NotificationRecipientRepository;
import com.licnosoft.linkedin.repository.notifications.NotificationRepository;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository entityRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final NotificationRecipientRepository notificationRecipientRepository;

    public NotificationServiceImpl(NotificationRepository entityRepository, UserService userService, ModelMapper modelMapper, NotificationRecipientRepository notificationRecipientRepository) {
        this.entityRepository = entityRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.notificationRecipientRepository = notificationRecipientRepository;
    }

    @Override
    public List<NotificationDTO> getFreshNotifications() {
        LocalDateTime startDate = DateUtil.toStartOfDay(LocalDate.now().minusDays(Constants.NOTIFICATION_FRESHNESS_DAYS));
        List<NotificationRecipient> result = notificationRecipientRepository.getFreshNotifications(userService.getLoggedUser().getId(), startDate);
        result.forEach(recipient -> {
            if (recipient.getReadDate() == null) {
                recipient.setReadDate(LocalDateTime.now());
                notificationRecipientRepository.save(recipient);
            }
        });
        return result.stream()
                .map(entity -> modelMapper.map(entity.getNotification(), NotificationDTO.class))
                .toList();
    }

    @Override
    public Page<NotificationDTO> findUserNotifications(String searchTerm, NotificationKind kind, LocalDateTime createdFrom, LocalDateTime createdTo, Pageable pageable) {
        return entityRepository.findUserNotifications(userService.getLoggedUser().getId(), searchTerm, kind, createdFrom, createdFrom, pageable)
                .map(entity -> modelMapper.map(entity, NotificationDTO.class));
    }

    @Override
    public void dismiss(Long id) {
        NotificationRecipient notificationRecipient = notificationRecipientRepository.findByUser_IdAndNotification_Id(userService.getLoggedUser().getId(), id)
                .orElseThrow(AppNotFoundException::new);
        notificationRecipient.setDismissedDate(LocalDateTime.now());
        notificationRecipientRepository.save(notificationRecipient);
    }


    @Override
    public void addNotification(NotificationKind kind, String title, List<User> users, String message, LocalDateTime endDate, String url) {
        entityRepository.save(new Notification(kind, title, message, endDate, users, url, userService.getOptionalLoggedUser()));
    }
}
