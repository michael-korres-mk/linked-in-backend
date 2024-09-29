package com.licnosoft.linkedin.model.jobs;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;

import java.util.List;

public class JobDetailedDTO extends JobBasicDTO {
	protected List<BasicEntityDTO> skills;
	protected List<JobApplicationDTO> applications;

	public List<BasicEntityDTO> getSkills() {return skills;}

	public void setSkills(List<BasicEntityDTO> skills) {this.skills = skills;}

	public List<JobApplicationDTO> getApplications() {return applications;}

	public void setApplications(List<JobApplicationDTO> applications) {this.applications = applications;}

}
