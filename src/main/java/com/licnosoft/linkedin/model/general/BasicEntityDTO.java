package com.licnosoft.linkedin.model.general;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.config.Constants;

import java.time.LocalDateTime;

public class BasicEntityDTO extends PlainEntityDTO {
    protected String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
	protected LocalDateTime creationDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
