package com.licnosoft.linkedin.domain.enumeration;

import java.io.Serializable;

public enum WorkplaceType implements Serializable {

	ON_SITE("On-site"),
	REMOTE("Remote"),
	HYBRID("Hybrid");
	private final String label;

	WorkplaceType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
