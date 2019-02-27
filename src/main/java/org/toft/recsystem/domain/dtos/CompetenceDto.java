package org.toft.recsystem.domain.dtos;

import lombok.Getter;
import org.toft.recsystem.domain.Competence;

@Getter
public class CompetenceDto {
    private String name;

    public CompetenceDto(Competence competence) {
        this.name = competence.getName();
    }
}
