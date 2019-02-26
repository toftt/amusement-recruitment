package org.toft.recsystem.services;

import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.dtos.CompetenceDTO;

import java.util.List;

public interface CompetenceService {
    Competence findCompetenceById(Long id);
    List<CompetenceDTO> findAllCompetences();
}
