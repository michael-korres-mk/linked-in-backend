package com.licnosoft.linkedin.model.jobs;

import java.util.List;

public class JobDetailedDTO extends JobBasicDTO {
	List<JobApplicationDTO> applications;

	public List<JobApplicationDTO> getApplications() {return applications;}

	public void setApplications(List<JobApplicationDTO> applications) {this.applications = applications;}
}
