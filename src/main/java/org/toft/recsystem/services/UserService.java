package org.toft.recsystem.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.toft.recsystem.domain.exceptions.EmailAlreadyExistsException;
import org.toft.recsystem.domain.WebsiteUser;
import org.toft.recsystem.repositories.UserRepository;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final WebsiteUser user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        return new User(user.getUsername(), user.getPassword(), emptyList());
    }

    public WebsiteUser registerNewUser(final WebsiteUser user) {
        if (emailExists(user.getEmail())) {
            throw new EmailAlreadyExistsException("WebsiteUser with email " + user.getEmail() + "already exists." );
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }


    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
