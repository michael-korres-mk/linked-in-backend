package com.licnosoft.linkedin.config;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd '|' HH:mm:ss");

	// Converts from String to LocalDateTime (used during deserialization)
	@Override
	public LocalDateTime unmarshal(String v) {
		return (v != null) ? LocalDateTime.parse(v, formatter) : null;
	}

	// Converts from LocalDateTime to String (used during serialization)
	@Override
	public String marshal(LocalDateTime v) {
		return (v != null) ? v.format(formatter) : null;
	}
}