package com.licnosoft.linkedin.model.jobs;

import com.licnosoft.linkedin.model.users.UserBasicDTO;

public class JobApplicationDTO extends UserBasicDTO {
	protected Long jobId;
	protected UserBasicDTO creator;

	public Long getJobId() {return jobId;}

	public void setJobId(Long jobId) {this.jobId = jobId;}

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}
}
