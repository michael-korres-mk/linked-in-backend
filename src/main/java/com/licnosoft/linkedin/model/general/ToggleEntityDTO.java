package com.licnosoft.linkedin.model.general;

import java.io.Serializable;

public class ToggleEntityDTO implements Serializable {

    private Long id;
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

