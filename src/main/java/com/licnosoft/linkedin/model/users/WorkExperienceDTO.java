package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import com.licnosoft.linkedin.model.general.ProfileInfoItemDTO;

public class WorkExperienceDTO extends ProfileInfoItemDTO {
	protected String company;
	protected WorkplaceType workplaceType;
	protected EmploymentType employmentType;

	public String getCompany() {return company;}

	public void setCompany(String company) {this.company = company;}

	public WorkplaceType getWorkplaceType() {return workplaceType;}

	public void setWorkplaceType(WorkplaceType workplaceType) {this.workplaceType = workplaceType;}

	public EmploymentType getEmploymentType() {return employmentType;}

	public void setEmploymentType(EmploymentType employmentType) {this.employmentType = employmentType;}

}
