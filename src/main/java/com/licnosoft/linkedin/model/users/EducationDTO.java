package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.model.general.ProfileInfoItemDTO;

public class EducationDTO extends ProfileInfoItemDTO {
	protected String organization;
	protected String field;
	protected String degree;
	protected Float grade;

	public String getOrganization() {return organization;}

	public void setOrganization(String organization) {this.organization = organization;}

	public String getField() {return field;}

	public void setField(String field) {this.field = field;}

	public String getDegree() {return degree;}

	public void setDegree(String degree) {this.degree = degree;}

	public Float getGrade() {return grade;}

	public void setGrade(Float grade) {this.grade = grade;}
}
