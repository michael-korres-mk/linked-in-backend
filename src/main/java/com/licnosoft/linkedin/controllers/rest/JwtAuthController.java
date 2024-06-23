package com.licnosoft.linkedin.controllers.rest;


import com.licnosoft.linkedin.config.security.JwtUtil;
import com.licnosoft.linkedin.model.auth.UserInfoDTO;
import com.licnosoft.linkedin.model.users.UserDTO;
import com.licnosoft.linkedin.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtAuthController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public JwtAuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestParam(name = "token",required = false) String token,
                                         @RequestParam(name = "email",required = false) String email,
                                         @RequestParam(name = "password",required = false) String password) {

        return new ResponseEntity<>(userService.loginUser(token, email, password), HttpStatus.OK);
    }

}
