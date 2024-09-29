package com.licnosoft.linkedin.model.enumeration;

import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.model.general.PlainListDTO;

public class EmploymentTypeDTO extends PlainListDTO {
	private EmploymentType employmentType;

	public EmploymentTypeDTO(EmploymentType employmentType) {
		super(employmentType.name(), employmentType.getLabel());
		this.employmentType = employmentType;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}
}
