package org.toft.recsystem.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.User;
import org.toft.recsystem.domain.Role;
import org.toft.recsystem.repositories.CompetenceRepository;
import org.toft.recsystem.repositories.UserRepository;
import org.toft.recsystem.repositories.RoleRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private CompetenceRepository competenceRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public BootstrapData(CompetenceRepository competenceRepository,
                         UserRepository userRepository,
                         RoleRepository roleRepository) {
        this.competenceRepository = competenceRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

        Role r1 = new Role();
        r1.setName("Recruiter");
        roleRepository.save(r1);

        User u1 = new User();
        u1.setEmail("jtoft@kth.se");
        u1.setName("Joachim");
        u1.setSurname("Toft");
        u1.setSsn("930529");
        u1.setRole(r1);
        u1.setUsername("toftt");
        u1.setPassword("password123");
        userRepository.save(u1);

        System.out.println("Competences saved: " + competenceRepository.count());
        System.out.println("People saved: " + userRepository.count());
        System.out.println("Roles saved: " + roleRepository.count());
    }
}
