package org.toft.recsystem.bootstrap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.toft.recsystem.domain.Competence;
import org.toft.recsystem.domain.Role;
import org.toft.recsystem.domain.WebsiteUser;
import org.toft.recsystem.domain.dtos.WebsiteUserDto;
import org.toft.recsystem.repositories.CompetenceRepository;
import org.toft.recsystem.repositories.UserRepository;
import org.toft.recsystem.repositories.RoleRepository;
import org.toft.recsystem.services.UserService;

@Component
public class BootstrapData implements CommandLineRunner {

    private CompetenceRepository competenceRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    public BootstrapData(CompetenceRepository competenceRepository,
                         UserRepository userRepository,
                         RoleRepository roleRepository,
                         UserService userService,
                         ModelMapper modelMapper) {
        this.competenceRepository = competenceRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading data...");

        Competence c1 = new Competence();
        c1.setName("Hot Dog Bbq");
        competenceRepository.save(c1);

        Competence c2 = new Competence();
        c2.setName("Rollerblades");
        competenceRepository.save(c2);

        Role r1 = new Role();
        r1.setName("Applicant");
        roleRepository.save(r1);

        WebsiteUserDto userDTO = WebsiteUserDto.builder()
                .firstName("Joachim")
                .lastName("Toft")
                .email("jtoft@kth.se")
                .socialSecurityNumber("930529")
                .username("toftt")
                .password("password123")
                .build();

        WebsiteUser user = modelMapper.map(userDTO, WebsiteUser.class);
        userService.registerNewUser(user);

        System.out.println("Competences saved: " + competenceRepository.count());
        System.out.println("People saved: " + userRepository.count());
        System.out.println("Roles saved: " + roleRepository.count());
    }
}
