package org.toft.recsystem.services;

import org.springframework.stereotype.Service;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.dtos.CompetenceDto;
import org.toft.recsystem.repositories.CompetenceRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CompetenceDto> findAllCompetences() {
        return competenceRepository
                .findAll()
                .stream()
                .map(CompetenceDto::new)
                .collect(Collectors.toList());
    }
}
