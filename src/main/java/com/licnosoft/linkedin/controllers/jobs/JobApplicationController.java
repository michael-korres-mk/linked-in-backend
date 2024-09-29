package com.licnosoft.linkedin.controllers.jobs;

import com.licnosoft.linkedin.model.jobs.JobApplicationDTO;
import com.licnosoft.linkedin.services.jobs.JobApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job-applications")
public class JobApplicationController {

	private static final Logger log = LoggerFactory.getLogger(JobApplicationController.class);

	private final JobApplicationService entityService;

	@Autowired
	public JobApplicationController(JobApplicationService entityService) {
		this.entityService = entityService;
	}

	@GetMapping("/search")
	public ResponseEntity<Page<JobApplicationDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "creator", required = false) Long creatorId, Pageable pageable) {
		return new ResponseEntity<>(entityService.
				search(searchTerm, creatorId, pageable), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<JobApplicationDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "creator", required = false) Long creatorId) {
		return new ResponseEntity<>(entityService.
				searchAsList(searchTerm, creatorId), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobApplicationDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		entityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<JobApplicationDTO> save(@RequestBody JobApplicationDTO dto) {
		return handleSaveRequest(dto, true);
	}

	private ResponseEntity<JobApplicationDTO> handleSaveRequest(JobApplicationDTO dto, boolean insert) {
		log.debug("REST request to save a job application : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}

}
