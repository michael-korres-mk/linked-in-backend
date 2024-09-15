package com.licnosoft.linkedin.services.jobs;

import com.licnosoft.linkedin.domain.jobs.JobApplication;
import com.licnosoft.linkedin.model.jobs.JobApplicationDTO;

public interface JobApplicationService {
	JobApplicationDTO findById(Long id);

	JobApplication findEntityById(Long id);

	JobApplicationDTO save(JobApplicationDTO dto, boolean insert);

	JobApplication save(JobApplication entity);

	void deleteById(Long id);
}
