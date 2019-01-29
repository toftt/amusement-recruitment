package org.toft.recsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toft.recsystem.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {}
