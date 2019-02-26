package org.toft.recsystem.domain.dtos;

import lombok.Getter;
import org.toft.recsystem.domain.Competence;

@Getter
public class CompetenceDTO {
    private String name;

    public CompetenceDTO(Competence competence) {
        this.name = competence.getName();
    }
}
