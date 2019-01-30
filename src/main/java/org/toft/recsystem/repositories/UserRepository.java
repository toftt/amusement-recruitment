package org.toft.recsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toft.recsystem.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
