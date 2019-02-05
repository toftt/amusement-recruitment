package org.toft.recsystem.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.UserDTO;
import org.toft.recsystem.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    static final String BASE_URL = "/api/v1/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDTO userDTO) {
        return userService.registerNewUser(userDTO);
    }

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(
            @AuthenticationPrincipal UsernamePasswordAuthenticationToken authenticationToken) {

        Map<Object, Object> model = new HashMap<>();
        model.put("username", authenticationToken.getPrincipal());
        return ResponseEntity.ok(model);
    }
}
