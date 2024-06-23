package com.licnosoft.linkedin.domain.enumeration;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum UserRole implements Serializable, GrantedAuthority {
	ADMIN("Admin"),
	PROFESSIONAL("Professional");
	private final String label;

	UserRole(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String getAuthority() {
		System.out.println(this.name());
		return this.name();
	}
}
