package com.licnosoft.linkedin.controllers.jobs;

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
//    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<JobDetailedDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "enabled", required = false) Boolean enabled, Pageable pageable) {
		return new ResponseEntity<>(entityService.
				search(searchTerm, enabled, pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('users_view')")
	public ResponseEntity<JobDetailedDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@PutMapping("/enabled")
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<ToggleEntityDTO> toggleEnabled(@RequestBody ToggleEntityDTO dto) {
		return new ResponseEntity<>(entityService.toggleEnabled(dto), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		entityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<JobDetailedDTO> save(@RequestBody JobDetailedDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<JobDetailedDTO> update(@RequestBody JobDetailedDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<JobDetailedDTO> handleSaveRequest(JobDetailedDTO dto, boolean insert) {
		log.debug("REST request to save a job : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}

}
