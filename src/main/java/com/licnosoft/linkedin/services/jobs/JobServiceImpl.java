package com.licnosoft.linkedin.services.jobs;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.config.exceptions.AppValidationException;
import com.licnosoft.linkedin.domain.jobs.Job;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.jobs.JobDetailedDTO;
import com.licnosoft.linkedin.repository.jobs.JobRepository;
import com.licnosoft.linkedin.services.general.FileService;
import com.licnosoft.linkedin.services.users.UserService;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobServiceImpl implements JobService {

	private final JobRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final FileService fileService;

	public JobServiceImpl(JobRepository entityRepository, ModelMapper modelMapper, UserService userService, FileService fileService) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.fileService = fileService;
	}

	@Override
	public Page<JobDetailedDTO> search(String searchTerm, Boolean enabled, Pageable pageable) {
		return entityRepository.search(searchTerm, pageable)
				.map(entity -> modelMapper.map(entity, JobDetailedDTO.class));
	}

	@Override
	public JobDetailedDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), JobDetailedDTO.class);
	}

	@Override
	public Job findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(AppNotFoundException::new);
	}


	@Override
	public ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto) {
		Job result = findEntityById(dto.getId());
		result.setEnabled(dto.isEnabled());
		return modelMapper.map(save(result), ToggleEntityDTO.class);
	}

	@Override
	public JobDetailedDTO save(JobDetailedDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) {
			throw new AppValidationException();
		}
		User creator = userService.getLoggedUser();
		Job entity = modelMapper.map(dto, Job.class);
		if (insert) {
			entity.setCreator(creator);
			entity.setCreationDate(LocalDateTime.now());
		}

		JobDetailedDTO jobDetailedDTO = modelMapper.map(save(entity), JobDetailedDTO.class);

//		NotificationKind kind = (insert)?NotificationKind.ANNOUNCEMENT_CREATED:NotificationKind.ANNOUNCEMENT_UPDATED;
//		notificationService.addInAppEmailNotificationToProviders(kind, entity.getTitle(), entity.getDescription(),entity.getMarket(),"/announcements/" + entity.getId() + "?view=1");

		return jobDetailedDTO;

	}

	@Override
	public Job save(Job entity) {
		if (!validateFields(entity)) {
			throw new AppValidationException();
		}

		return entityRepository.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		entityRepository.deleteById(id);
	}

	private boolean validateFields(Job entity){
		return (entity.getCreator() != null) && (entity.getCreationDate() != null);
	}

}
