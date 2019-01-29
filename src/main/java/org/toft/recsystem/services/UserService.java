package org.toft.recsystem.services;

import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.UserDTO;

public interface UserService {
    User registerNewUser(UserDTO userDTO);
}
