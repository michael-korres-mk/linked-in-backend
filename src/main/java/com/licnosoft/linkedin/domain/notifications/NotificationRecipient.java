package com.licnosoft.linkedin.domain.notifications;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_recipients")
public class NotificationRecipient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "notification_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = ValidationErrorMessages.NOTIFICATION_NOT_BLANK)
    private Notification notification;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = ValidationErrorMessages.USER_NOT_BLANK)
    private User user;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "read_date")
    private LocalDateTime readDate;

    @Column(name = "dismissed_date")
    private LocalDateTime dismissedDate;

    public NotificationRecipient() {
    }

    public NotificationRecipient(Notification notification, User user) {
        this.notification = notification;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public LocalDateTime getSentDate() {return sentDate;}

    public void setSentDate(LocalDateTime sentDate) {this.sentDate = sentDate;}

    public LocalDateTime getReadDate() {
        return readDate;
    }

    public void setReadDate(LocalDateTime readDate) {
        this.readDate = readDate;
    }

    public LocalDateTime getDismissedDate() {
        return dismissedDate;
    }

    public void setDismissedDate(LocalDateTime dismissedDate) {
        this.dismissedDate = dismissedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationRecipient that = (NotificationRecipient) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NotificationRecipient{" +
                "id=" + id +
                ", notification=" + notification +
                ", user=" + user +
                ", readTime=" + readDate +
                ", dismissedTime=" + dismissedDate +
                '}';
    }
}
