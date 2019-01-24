package org.toft.recsystem.services;

import org.springframework.stereotype.Service;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.repositories.CompetenceRepository;

import java.util.List;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    public Competence findCompetenceById(Long id) {
        return competenceRepository.findById(id).get();
    }

    @Override
    public List<Competence> findAllCompetences() {
        return competenceRepository.findAll();
    }
}
