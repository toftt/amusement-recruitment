package org.toft.recsystem.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.repositories.CompetenceRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CompetenceRepository competenceRepository;

    public BootstrapData(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading data...");

        Competence c1 = new Competence();
        c1.setName("Hot Dog Bbq");
        competenceRepository.save(c1);

        Competence c2 = new Competence();
        c2.setName("Rollerblades ");
        competenceRepository.save(c2);

        System.out.println("Competences saved: " + competenceRepository.count());
    }
}
