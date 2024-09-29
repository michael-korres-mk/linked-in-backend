package com.licnosoft.linkedin.model.enumeration;

import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import com.licnosoft.linkedin.model.general.PlainListDTO;

public class WorkplaceTypeDTO extends PlainListDTO {
    private WorkplaceType workplaceType;

    public WorkplaceTypeDTO(WorkplaceType workplaceType) {
        super(workplaceType.name(), workplaceType.getLabel());
        this.workplaceType = workplaceType;
    }

    public WorkplaceType getWorkplaceType() {
        return workplaceType;
    }

    public void setWorkplaceType(WorkplaceType workplaceType) {
        this.workplaceType = workplaceType;
    }
}
