package com.licnosoft.linkedin.controllers.posts;

import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.PostDTO;
import com.licnosoft.linkedin.services.posts.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

	private static final Logger log = LoggerFactory.getLogger(PostController.class);

	private final PostService entityService;

	@Autowired
	public PostController(PostService entityService) {
		this.entityService = entityService;
	}

	@GetMapping("/search")
//    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<PostDTO>> search(
			@RequestParam(value = "q", required = false) String searchTerm,
			@RequestParam(value = "enabled", required = false) Boolean enabled, Pageable pageable) {
		return new ResponseEntity<>(entityService.
				search(searchTerm, enabled, pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('users_view')")
	public ResponseEntity<PostDTO> findById(@PathVariable Long id) {
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
	public ResponseEntity<PostDTO> save(@RequestBody PostDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
//    @PreAuthorize("hasAuthority('users_edit')")
	public ResponseEntity<PostDTO> update(@RequestBody PostDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<PostDTO> handleSaveRequest(PostDTO dto, boolean insert) {
		log.debug("REST request to save a user : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}

}
