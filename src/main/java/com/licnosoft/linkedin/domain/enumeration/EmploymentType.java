package com.licnosoft.linkedin.domain.enumeration;

import java.io.Serializable;

public enum EmploymentType implements Serializable {
	FULL_TIME("Full Time"),
	PART_TIME("Part Time"),
	CONTRACT("Contract"),
	INTERNSHIP("Internship");

	private final String label;

	EmploymentType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

