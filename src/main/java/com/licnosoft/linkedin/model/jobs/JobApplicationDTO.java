package com.licnosoft.linkedin.model.jobs;

import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;

public class JobApplicationDTO extends UserBasicDTO {
	protected UserBasicDTO creator;
	protected PlainEntityDTO job;

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}

	public PlainEntityDTO getJob() {return job;}

	public void setJob(PlainEntityDTO job) {this.job = job;}
}
