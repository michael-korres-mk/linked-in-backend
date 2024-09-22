package com.licnosoft.linkedin.model.jobs;

import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

public class JobBasicDTO extends BasicEntityDTO {
	protected UserBasicDTO creator;
	protected String company;
	protected WorkplaceType workplaceType;
	protected EmploymentType employmentType;

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}

	public String getCompany() {return company;}

	public void setCompany(String company) {this.company = company;}

	public WorkplaceType getWorkplaceType() {return workplaceType;}

	public void setWorkplaceType(WorkplaceType workplaceType) {this.workplaceType = workplaceType;}

	public EmploymentType getEmploymentType() {return employmentType;}

	public void setEmploymentType(EmploymentType employmentType) {this.employmentType = employmentType;}
}
