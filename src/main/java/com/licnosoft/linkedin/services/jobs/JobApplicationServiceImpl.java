package com.licnosoft.linkedin.services.jobs;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.jobs.JobApplication;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.jobs.JobApplicationDTO;
import com.licnosoft.linkedin.repository.jobs.JobApplicationRepository;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class JobApplicationServiceImpl implements JobApplicationService {

	private final JobApplicationRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final JobService jobService;

	public JobApplicationServiceImpl(JobApplicationRepository entityRepository, ModelMapper modelMapper, UserService userService,
	                                 JobService jobService) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.jobService = jobService;
	}

	@Override
	public JobApplicationDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), JobApplicationDTO.class);
	}

	@Override
	public JobApplication findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αίτηση με αυτό το id: " + id +"."));
	}

	@Override
	public JobApplicationDTO save(JobApplicationDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) throw new AppValidationException();

		User creator = userService.getLoggedUser();
		JobApplication entity = modelMapper.map(dto, JobApplication.class);
		if (insert) {
			entity.setCreator(creator);
			entity.setCreationDate(LocalDateTime.now());
			entity.setJob(jobService.findEntityById(dto.getJobId()));
		}

		JobApplicationDTO jobDetailedDTO = modelMapper.map(save(entity), JobApplicationDTO.class);

		return jobDetailedDTO;

	}

	@Override
	public JobApplication save(JobApplication entity) {
		if (!validateFields(entity)) throw new AppValidationException();

		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	private boolean validateFields(JobApplication entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}

}
