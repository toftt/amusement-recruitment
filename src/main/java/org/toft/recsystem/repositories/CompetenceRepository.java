package org.toft.recsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.toft.recsystem.domain.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {}
