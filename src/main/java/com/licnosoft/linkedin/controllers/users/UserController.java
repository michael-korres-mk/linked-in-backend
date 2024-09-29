package com.licnosoft.linkedin.controllers.users;

import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;
import com.licnosoft.linkedin.model.general.ToggleEntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;
import com.licnosoft.linkedin.model.users.UserDetailedDTO;
import com.licnosoft.linkedin.model.users.UserProfileDTO;
import com.licnosoft.linkedin.services.users.UserService;
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
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService entityService;

    @Autowired
    public UserController(UserService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<UserBasicDTO>> search(
            @RequestParam(value = "q", required = false) String searchTerm,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "userRole", required = false) UserRole userRole,
            @RequestParam(value = "exceptLoggedUser", required = false) Boolean exceptLoggedUser,
            @RequestParam(value = "enabled", required = false) Boolean enabled, Pageable pageable) {
        return new ResponseEntity<>(entityService.
                search(searchTerm, firstName,  lastName,  email, userRole, exceptLoggedUser,enabled, pageable), HttpStatus.OK);
    }

    @GetMapping("/as-list/search")
    public ResponseEntity<List<UserDetailedDTO>> asListSearch(
            @RequestParam(value = "q", required = false) String searchTerm,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "userRole", required = false) UserRole userRole,
            @RequestParam(value = "exceptLoggedUser", required = false) Boolean exceptLoggedUser,
            @RequestParam(value = "enabled", required = false) Boolean enabled) {
        return new ResponseEntity<>(entityService.
                asListSearch(searchTerm, firstName,  lastName,  email, userRole, exceptLoggedUser, enabled), HttpStatus.OK);
    }

    @GetMapping("/roles/list")
    public ResponseEntity<List<UserRole>> getSubmissionStatuses() {
        return new ResponseEntity<>(Arrays.stream(UserRole.values()).toList(), HttpStatus.OK);
    }

//    @GetMapping("/connection-requests/list")
//    public ResponseEntity<List<UserConnectionDTO>> getUserConnectionRequests() {
//        return new ResponseEntity<>(entityService.getUserConnectionRequests(false), HttpStatus.OK);
//    }
    @GetMapping("/list")
    public ResponseEntity<List<PlainEntityDTO>> getPlainList() {
        return new ResponseEntity<>(entityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBasicDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long id) {
        return new ResponseEntity<>(entityService.findProfileById(id), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateProfile(@RequestBody UserProfileDTO dto) {
        return new ResponseEntity<>(entityService.updateProfile(dto), HttpStatus.OK);
    }

    @GetMapping("/settings/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Long id) {
        return new ResponseEntity<>(entityService.findUserInfoById(id), HttpStatus.OK);
    }

    @PutMapping("/settings")
    public ResponseEntity<UserBasicDTO> updateUserInfo(@RequestBody UserInfoDTO dto) {
        return new ResponseEntity<>(entityService.updateUserInfo(dto), HttpStatus.OK);
    }

    @PutMapping("/enabled")
    public ResponseEntity<ToggleEntityDTO> toggleEnabled(@RequestBody ToggleEntityDTO dto) {
        return new ResponseEntity<>(entityService.toggleEnabled(dto), HttpStatus.OK);
    }

    @DeleteMapping(path={"/{id}","/as-list/{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        entityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserInfoDTO> save(@RequestBody UserInfoDTO dto) {
        return handleSaveRequest(dto, true);
    }

    @PutMapping
    public ResponseEntity<UserInfoDTO> update(@RequestBody UserInfoDTO dto) {
        return handleSaveRequest(dto, false);
    }

    private ResponseEntity<UserInfoDTO> handleSaveRequest(UserInfoDTO dto, boolean insert) {
        log.debug("REST request to save a user : {}", dto);
        return new ResponseEntity<>(entityService.save(dto, insert), HttpStatus.CREATED);
    }
}
