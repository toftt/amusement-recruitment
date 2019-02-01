package org.toft.recsystem.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.Role;
import org.toft.recsystem.domain.UserDTO;
import org.toft.recsystem.repositories.CompetenceRepository;
import org.toft.recsystem.repositories.UserRepository;
import org.toft.recsystem.repositories.RoleRepository;
import org.toft.recsystem.services.UserServiceImpl;

@Component
public class BootstrapData implements CommandLineRunner {

    private CompetenceRepository competenceRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserServiceImpl userService;

    public BootstrapData(CompetenceRepository competenceRepository,
                         UserRepository userRepository,
                         RoleRepository roleRepository,
                         UserServiceImpl userService) {
        this.competenceRepository = competenceRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
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
        r1.setName("Applicant");
        roleRepository.save(r1);

        UserDTO u1 = new UserDTO();
        u1.setEmail("jtoft@kth.se");
        u1.setFirstName("Joachim");
        u1.setLastName("Toft");
        u1.setSocialSecurityNumber("930529");
        u1.setUsername("toftt");
        u1.setPassword("password123");
        userService.registerNewUser(u1);

        System.out.println("Competences saved: " + competenceRepository.count());
        System.out.println("People saved: " + userRepository.count());
        System.out.println("Roles saved: " + roleRepository.count());
    }
}
