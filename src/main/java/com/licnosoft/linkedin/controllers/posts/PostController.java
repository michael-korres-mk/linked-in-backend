package com.licnosoft.linkedin.controllers.posts;

import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.posts.CommentDTO;
import com.licnosoft.linkedin.model.posts.PostDetailedDTO;
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
	public ResponseEntity<Page<PostDetailedDTO>> search(Pageable pageable) {
		return new ResponseEntity<>(entityService.
				search(pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDetailedDTO> findById(@PathVariable Long id) {
		return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/comment")
	public ResponseEntity<CommentDTO> comment(@RequestBody CommentDTO dto) {
		return new ResponseEntity<>(entityService.comment(dto), HttpStatus.OK);
	}

	@PutMapping("/like/enabled")
	public ResponseEntity<ToggleEntityDTO> toggleLike(@RequestBody ToggleEntityDTO dto) {
		return new ResponseEntity<>(entityService.toggleLike(dto), HttpStatus.OK);
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
	public ResponseEntity<PostDetailedDTO> save(@RequestBody PostDetailedDTO dto) {
		return handleSaveRequest(dto, true);
	}

	@PutMapping
	public ResponseEntity<PostDetailedDTO> update(@RequestBody PostDetailedDTO dto) {
		return handleSaveRequest(dto, false);
	}

	private ResponseEntity<PostDetailedDTO> handleSaveRequest(PostDetailedDTO dto, boolean insert) {
		log.debug("REST request to save a user : {}", dto);
		return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
	}

}
