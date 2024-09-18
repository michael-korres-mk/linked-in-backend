package com.licnosoft.linkedin.controllers.network;

import com.licnosoft.linkedin.model.network.UserConnectionDTO;
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

	@GetMapping("/search")
	public ResponseEntity<UserConnectionDTO> findRequests(@RequestParam(name = "recipient") Long recipientId, @RequestParam(name = "accepted") Boolean accepted) {
		return new ResponseEntity<>(entityService.findRequests(recipientId,accepted), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserConnectionDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		entityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserConnectionDTO> save(@RequestBody UserConnectionDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
	public ResponseEntity<UserConnectionDTO> update(@RequestBody UserConnectionDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<UserConnectionDTO> handleSaveRequest(UserConnectionDTO dto, boolean insert) {
		log.debug("REST request to save a user : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}


}
