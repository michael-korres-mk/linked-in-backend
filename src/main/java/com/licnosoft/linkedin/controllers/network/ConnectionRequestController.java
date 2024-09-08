package com.licnosoft.linkedin.controllers.network;

import com.licnosoft.linkedin.model.network.ConnectionRequestDTO;
import com.licnosoft.linkedin.services.network.ConnectionRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connection-requests")
public class ConnectionRequestController {

	private static final Logger log = LoggerFactory.getLogger(ConnectionRequestController.class);

	private final ConnectionRequestService entityService;

	@Autowired
	public ConnectionRequestController(ConnectionRequestService entityService) {
		this.entityService = entityService;
	}

	@GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('users_view')")
	public ResponseEntity<ConnectionRequestDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		entityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<ConnectionRequestDTO> save(@RequestBody ConnectionRequestDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<ConnectionRequestDTO> update(@RequestBody ConnectionRequestDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<ConnectionRequestDTO> handleSaveRequest(ConnectionRequestDTO dto, boolean insert) {
		log.debug("REST request to save a user : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}


}
