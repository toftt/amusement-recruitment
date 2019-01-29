package org.toft.recsystem.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.UserDTO;
import org.toft.recsystem.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setSocialSecurityNumber(userDTO.getSocialSecurityNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}
