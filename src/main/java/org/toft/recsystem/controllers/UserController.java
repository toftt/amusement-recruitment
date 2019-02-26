package org.toft.recsystem.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.dtos.UserDTO;
import org.toft.recsystem.services.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    static final String BASE_URL = "/api/v1/users";

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveUser(@RequestBody final UserDTO userDTO) {
        final User user = convertToEntity(userDTO);
        User createdUser = userService.registerNewUser(user);
        return convertToDto(createdUser);
    }

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(
            @AuthenticationPrincipal UsernamePasswordAuthenticationToken authenticationToken) {

        Map<Object, Object> model = new HashMap<>();
        model.put("username", authenticationToken.getPrincipal());
        return ResponseEntity.ok(model);
    }

    private UserDTO convertToDto(final User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToEntity(final UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
