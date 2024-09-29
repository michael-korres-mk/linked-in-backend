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
import com.licnosoft.linkedin.util.JobUtil;
import com.licnosoft.linkedin.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class JobServiceImpl implements JobService {

	private final JobRepository entityRepository;
	private final ModelMapper modelMapper;
	private final UserService userService;
	private final FileService fileService;
	private final JobUtil jobUtil;

	public JobServiceImpl(JobRepository entityRepository, ModelMapper modelMapper, UserService userService, FileService fileService, JobUtil jobUtil) {
		this.entityRepository = entityRepository;
		this.modelMapper = modelMapper;
		this.userService = userService;
		this.fileService = fileService;
		this.jobUtil = jobUtil;
	}

	@Override
	public Page<JobDetailedDTO> search(String searchTerm,Boolean exceptLoggedUser,Long creatorId, Pageable pageable) {
		Long loggedUserId = (exceptLoggedUser != null)? userService.getLoggedUser().getId() : null;
		return entityRepository.search(searchTerm,loggedUserId, creatorId, pageable)
				.map(entity -> modelMapper.map(entity, JobDetailedDTO.class));
	}

	@Override
	public List<JobDetailedDTO> searchAsList(String searchTerm,Boolean exceptLoggedUser, Long creatorId) {
		Long loggedUserId = (exceptLoggedUser != null)? userService.getLoggedUser().getId() : null;
		return entityRepository.searchAsList(searchTerm,loggedUserId, creatorId).stream().map(j -> modelMapper.map(j, JobDetailedDTO.class)).toList();
	}

	@Override
	public JobDetailedDTO findById(Long id) {
		return modelMapper.map(findEntityById(id), JobDetailedDTO.class);
	}

	@Override
	public Job findEntityById(Long id) {
		return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αγγελία με αυτό το id: " + id + "."));
	}

	@Override
	public ToggleEntityDTO toggleEnabled(ToggleEntityDTO dto) {
		Job result = findEntityById(dto.getId());
		result.setEnabled(dto.isEnabled());
		return modelMapper.map(save(result), ToggleEntityDTO.class);
	}

	@Override
	public JobDetailedDTO save(JobDetailedDTO dto, boolean insert) {
		if (!Utilities.validateEntityDto(dto, insert)) throw new AppValidationException();

		User creator = userService.getLoggedUser();
		Job newEntity = modelMapper.map(dto, Job.class);
		if (insert) {
			newEntity.setCreator(creator);
			newEntity.setCreationDate(LocalDateTime.now());

			return modelMapper.map(save(newEntity), JobDetailedDTO.class);
		}
		else{
			Job currentEntity = findEntityById(newEntity.getId());
			currentEntity.setTitle(newEntity.getTitle());
			currentEntity.setDescription(newEntity.getDescription());
			currentEntity.setCompany(newEntity.getCompany());
			currentEntity.setLocation(newEntity.getLocation());
			currentEntity.setWorkplaceType(newEntity.getWorkplaceType());
			currentEntity.setEmploymentType(newEntity.getEmploymentType());

			jobUtil.updateSkills(currentEntity,newEntity);

			return modelMapper.map(save(currentEntity), JobDetailedDTO.class);
		}
	}

	@Override
	public Job save(Job entity) {
		if (!validateFields(entity)) throw new AppValidationException();

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
