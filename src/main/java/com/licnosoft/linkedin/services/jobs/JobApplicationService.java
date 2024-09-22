package com.licnosoft.linkedin.services.jobs;

import com.licnosoft.linkedin.domain.jobs.JobApplication;
import com.licnosoft.linkedin.model.jobs.JobApplicationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobApplicationService {

	Page<JobApplicationDTO> search(String searchTerm, Long creatorId, Pageable pageable);
	List<JobApplicationDTO> searchAsList(String searchTerm, Long creatorId);

	JobApplicationDTO findById(Long id);

	JobApplication findEntityById(Long id);

	JobApplicationDTO save(JobApplicationDTO dto, boolean insert);

	JobApplication save(JobApplication entity);

	void deleteById(Long id);
}
