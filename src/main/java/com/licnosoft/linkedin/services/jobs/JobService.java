package com.licnosoft.linkedin.services.jobs;

import com.licnosoft.linkedin.domain.jobs.Job;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.jobs.JobDetailedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
	Page<JobDetailedDTO> search(String searchTerm, Long creatorId, Pageable pageable);
	List<JobDetailedDTO> searchAsList(String searchTerm, Long creatorId);

	JobDetailedDTO findById(Long id);

	Job findEntityById(Long id);

	ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto);

	JobDetailedDTO save(JobDetailedDTO dto, boolean insert);

	Job save(Job entity);

	void deleteById(Long id);

}
