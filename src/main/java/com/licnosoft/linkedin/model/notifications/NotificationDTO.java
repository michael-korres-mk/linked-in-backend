package com.licnosoft.linkedin.model.notifications;

import com.licnosoft.linkedin.config.LocalDateTimeAdapter;
import com.licnosoft.linkedin.domain.enumeration.NotificationKind;
import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class NotificationDTO extends BasicEntityDTO {

    private NotificationKind kind;
    protected LocalDateTime readDate;
    protected LocalDateTime endDate;
    private PlainEntityDTO creator;
    private String url;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "end-date")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "read-date")
    public LocalDateTime getReadDate() { return readDate; }

    public void setReadDate(LocalDateTime readDate) { this.readDate = readDate; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public NotificationKind getKind() { return kind; }

    public void setKind(NotificationKind kind) { this.kind = kind; }

    public PlainEntityDTO getCreator() { return creator; }

    public void setCreator(PlainEntityDTO creator) { this.creator = creator; }
}
