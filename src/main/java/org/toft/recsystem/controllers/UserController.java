package org.toft.recsystem.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.toft.recsystem.domain.WebsiteUser;
import org.toft.recsystem.domain.dtos.WebsiteUserDto;
import org.toft.recsystem.services.UserService;

import javax.validation.Valid;
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
    public WebsiteUserDto saveUser(@Valid @RequestBody final WebsiteUserDto userDTO) {
        final WebsiteUser user = convertToEntity(userDTO);
        WebsiteUser createdUser = userService.registerNewUser(user);
        return convertToDto(createdUser);
    }

    @GetMapping("/me")
    public ResponseEntity getCurrentUser(
            @AuthenticationPrincipal UsernamePasswordAuthenticationToken authenticationToken) {

        Map<Object, Object> model = new HashMap<>();
        model.put("username", authenticationToken.getPrincipal());
        return ResponseEntity.ok(model);
    }

    private WebsiteUserDto convertToDto(final WebsiteUser user) {
        return modelMapper.map(user, WebsiteUserDto.class);
    }

    private WebsiteUser convertToEntity(final WebsiteUserDto userDTO) {
        return modelMapper.map(userDTO, WebsiteUser.class);
    }
}
