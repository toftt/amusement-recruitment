package org.toft.recsystem.services;

import org.toft.recsystem.domain.Competence;

import java.util.List;

public interface CompetenceService {
    Competence findCompetenceById(Long id);
    List<Competence> findAllCompetences();
}
