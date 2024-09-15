package com.licnosoft.linkedin.model.jobs;

import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

public class JobApplicationDTO extends UserBasicDTO {
	protected Long jobId;
	protected PlainEntityDTO creator;

	public Long getJobId() {return jobId;}

	public void setJobId(Long jobId) {this.jobId = jobId;}

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}
}
