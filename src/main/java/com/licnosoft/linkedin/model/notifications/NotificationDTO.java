package com.licnosoft.linkedin.model.notifications;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class NotificationDTO extends BasicEntityDTO {

    private NotificationKind kind;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
    private LocalDateTime readDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
    private LocalDateTime endDate;
    private PlainEntityDTO creator;
    private String url;
    private String message;

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

    public LocalDateTime getReadDate() { return readDate; }

    public void setReadDate(LocalDateTime readDate) { this.readDate = readDate; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public NotificationKind getKind() { return kind; }

    public void setKind(NotificationKind kind) { this.kind = kind; }

    public PlainEntityDTO getCreator() { return creator; }

    public void setCreator(PlainEntityDTO creator) { this.creator = creator; }
}
