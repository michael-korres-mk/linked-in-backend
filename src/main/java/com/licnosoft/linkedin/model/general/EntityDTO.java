package com.licnosoft.linkedin.model.general;

import java.io.Serializable;

public class EntityDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    protected Long id;

    public EntityDTO() {
    }

    public EntityDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
