package org.toft.recsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toft.recsystem.domain.WebsiteUser;

public interface UserRepository extends JpaRepository<WebsiteUser, Long> {
    WebsiteUser findByEmail(String email);
    WebsiteUser findByUsername(String username);
}
