package com.licnosoft.linkedin.model.general;

import com.licnosoft.linkedin.config.LocalDateTimeAdapter;
import com.licnosoft.linkedin.domain.general.PlainEntity;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

public class PlainEntityDTO extends EntityDTO {
    protected String title;
    protected LocalDateTime creationDate;
    protected boolean enabled;

    public PlainEntityDTO() {}

    public PlainEntityDTO(PlainEntity plainEntity) {
        super(plainEntity.getId());
        this.title = plainEntity.getTitle();
        this.enabled = plainEntity.isEnabled();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @XmlElement(name = "creation-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreationDate() {return creationDate;}

    public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
}
