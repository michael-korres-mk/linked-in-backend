package com.licnosoft.linkedin.model.general;

import com.licnosoft.linkedin.domain.general.PlainEntity;

public class PlainEntityDTO extends EntityDTO {
    protected String title;
    protected boolean enabled;

    public PlainEntityDTO() {
    }

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
}
