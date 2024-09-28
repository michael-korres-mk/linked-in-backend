package com.licnosoft.linkedin.controllers.jobs;

import com.licnosoft.linkedin.domain.enumeration.EmploymentType;
import com.licnosoft.linkedin.domain.enumeration.WorkplaceType;
import com.licnosoft.linkedin.model.enumeration.EmploymentTypeDTO;
import com.licnosoft.linkedin.model.enumeration.WorkplaceTypeDTO;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.jobs.JobDetailedDTO;
import com.licnosoft.linkedin.services.jobs.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

	private static final Logger log = LoggerFactory.getLogger(JobController.class);

	private final JobService entityService;

	@Autowired
	public JobController(JobService entityService) {
		this.entityService = entityService;
	}

	@GetMapping("/search")
	public ResponseEntity<Page<JobDetailedDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "creator", required = false) Long creatorId,
			@RequestParam(value = "exceptLoggedUser", required = false) Boolean exceptLoggedUser,
			Pageable pageable) {
		return new ResponseEntity<>(entityService.
				search(searchTerm,exceptLoggedUser, creatorId, pageable), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<JobDetailedDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "creator", required = false) Long creatorId,
            @RequestParam(value = "exceptLoggedUser", required = false) Boolean exceptLoggedUser) {
		return new ResponseEntity<>(entityService.
				searchAsList(searchTerm,exceptLoggedUser, creatorId), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobDetailedDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/workplace-types/list")
	public ResponseEntity<List<WorkplaceTypeDTO>> getWorkplaceTypes() {
		return new ResponseEntity<>(Arrays.stream(WorkplaceType.values())
				.map(WorkplaceTypeDTO::new).toList(), HttpStatus.OK);
	}

	@GetMapping("/employment-types/list")
	public ResponseEntity<List<EmploymentTypeDTO>> getEmploymentTypes() {
		return new ResponseEntity<>(Arrays.stream(EmploymentType.values())
				.map(EmploymentTypeDTO::new).toList(), HttpStatus.OK);
	}



	@PutMapping("/enabled")
	public ResponseEntity<ToggleEntityDTO> toggleEnabled(@RequestBody ToggleEntityDTO dto) {
		return new ResponseEntity<>(entityService.toggleEnabled(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		entityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<JobDetailedDTO> save(@RequestBody JobDetailedDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
	public ResponseEntity<JobDetailedDTO> update(@RequestBody JobDetailedDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<JobDetailedDTO> handleSaveRequest(JobDetailedDTO dto, boolean insert) {
		log.debug("REST request to save a job : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}

}
