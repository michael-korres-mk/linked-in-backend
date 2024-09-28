package com.licnosoft.linkedin.domain.enumeration;

import java.io.Serializable;

public enum EmploymentType implements Serializable {
	FULL_TIME("Πλήρης Απασχόληση"),
	PART_TIME("Μερική Απασχόληση"),
	CONTRACT("Συμβόλαιο"),
	INTERNSHIP("Πρακτική");

	private final String label;

	EmploymentType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}

