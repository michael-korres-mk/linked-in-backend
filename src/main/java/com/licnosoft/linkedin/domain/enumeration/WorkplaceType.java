package com.licnosoft.linkedin.domain.enumeration;

import java.io.Serializable;

public enum WorkplaceType implements Serializable {

	ON_SITE("Στις Εγκαταστάσεις"),
	REMOTE("Εξ Αποστάσεως"),
	HYBRID("Υβριδικό");

	private final String label;

	WorkplaceType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
