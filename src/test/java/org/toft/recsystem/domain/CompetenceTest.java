package org.toft.recsystem.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.toft.recsystem.repositories.CompetenceRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, CompetenceTest.class,
                                     TransactionalTestExecutionListener.class})
@Transactional
@Commit
public class CompetenceTest implements TestExecutionListener {
    @Autowired
    private CompetenceRepository competenceRepository;

    @Test
    public void testCreateCompetence() {
        long before = competenceRepository.count();

        Competence c1 = new Competence();
        c1.setName("Hot Dog Bbq");
        competenceRepository.save(c1);
        assertEquals(before + 1, competenceRepository.count());
    }
}