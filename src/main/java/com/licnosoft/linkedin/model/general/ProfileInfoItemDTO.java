package com.licnosoft.linkedin.model.general;

import java.time.LocalDate;

public class ProfileInfoItemDTO extends BasicEntityDTO {
	protected LocalDate startDate;
	protected LocalDate endDate;
	protected String location;

	protected PlainEntityDTO creator;

	public LocalDate getStartDate() {return startDate;}

	public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

	public LocalDate getEndDate() {return endDate;}

	public void setEndDate(LocalDate endDate) {this.endDate = endDate;}

	public String getLocation() {return location;}

	public void setLocation(String location) {this.location = location;}

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}
}
