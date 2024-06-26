package com.licnosoft.linkedin.domain.notifications;

import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "kind")
    @NotNull(message = ValidationErrorMessages.CHANNEL_NOT_BLANK)
    private NotificationKind kind;

    @Column(name = "title")
    @NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "creation_date")
    @NotNull(message = ValidationErrorMessages.CREATION_NOT_BLANK)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<NotificationRecipient> recipients = new ArrayList<>();

    public Notification() {
    }

    public Notification(NotificationKind kind, String title, String message, LocalDateTime endDate, List<User> users, String url, User creator) {
        this.kind = kind;
        this.title = title;
        this.message = message;
        this.endDate = endDate;
        this.recipients = users.stream().map(user -> new NotificationRecipient(this, user)).toList();
        this.url = url;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationKind getKind() {
        return kind;
    }

    public void setKind(NotificationKind kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<NotificationRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<NotificationRecipient> recipients) {
        this.recipients = recipients;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification entity = (Notification) o;
        if (entity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Notification{"
                + "id=" + id
                + ", title=" + title
                + ", creationDate=" + creationDate
                + '}';
    }
}
