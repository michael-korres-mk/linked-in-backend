package com.licnosoft.linkedin.repository.notifications;

import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.notifications.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("""
            SELECT n FROM Notification n JOIN n.recipients nr WHERE (:searchTerm is null or n.title LIKE %:searchTerm%)
            AND (:kind is null or n.kind = :kind)
            AND (:userId is null or nr.user.id = :userId)
            AND (cast(:createdFrom as date) is null or n.creationDate >= :createdFrom)
            AND (cast(:createdTo as date) is null or n.creationDate <= :createdTo)
            GROUP BY n
            """)
    Page<Notification> findUserNotifications(
            @Param("userId") Long userId,
            @Param("searchTerm") String searchTerm,
            @Param("kind") NotificationKind kind,
            @Param("createdFrom") LocalDateTime createdFrom,
            @Param("createdTo") LocalDateTime createdTo, Pageable pageable);
}
