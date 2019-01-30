package org.toft.recsystem.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.toft.recsystem.domain.EmailAlreadyExistsException;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.UserDTO;

public interface UserService extends UserDetailsService {
    User registerNewUser(UserDTO userDTO) throws EmailAlreadyExistsException;
}
