package com.licnosoft.linkedin.repository.notifications;

import com.licnosoft.linkedin.domain.notifications.NotificationRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationRecipientRepository extends JpaRepository<NotificationRecipient, Long> {
    Optional<NotificationRecipient> findByUser_IdAndNotification_Id(Long userId, Long notificationId);

    @Query("""
            SELECT nr FROM NotificationRecipient nr JOIN nr.notification n WHERE nr.user.id = :userId AND nr.dismissedDate IS NULL
            AND n.creationDate >= :startDate
            """)
    List<NotificationRecipient> getFreshNotifications(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate);

    @Modifying
    void deleteAllByUser_Id(Long userId);

}
