package org.toft.recsystem.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.toft.recsystem.domain.EmailAlreadyExistsException;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.UserDTO;
import org.toft.recsystem.repositories.UserRepository;

import static java.util.Collections.emptyList;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                                                                      user.getPassword(),
                                                                      emptyList());
    }

    @Override
    public User registerNewUser(final UserDTO userDTO) {
        if (emailExists(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException("User with email " + userDTO.getEmail() + "already exists." );
        }

        final User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setSocialSecurityNumber(userDTO.getSocialSecurityNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(user);
    }


    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
