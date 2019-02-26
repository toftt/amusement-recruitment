package org.toft.recsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.CompetenceDTO;
import org.toft.recsystem.services.CompetenceService;

import java.util.List;

@RestController
@RequestMapping(CompetenceController.BASE_URL)
public class CompetenceController {

    static final String BASE_URL = "/api/v1/competences";

    private final CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService) {
        this.competenceService = competenceService;
    }

    @GetMapping
    List<CompetenceDTO> getAllCompetences() {
        return competenceService.findAllCompetences();
    }
}
