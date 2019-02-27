package org.toft.recsystem.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.toft.recsystem.domain.WebsiteUser;
import org.toft.recsystem.domain.dtos.WebsiteUserDto;
import org.toft.recsystem.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebsiteUserServiceImplTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private WebsiteUserDto userDTO;

    @BeforeAll
    void setupUserDTO() {
        userDTO = new WebsiteUserDto();

        userDTO.setUsername("Joachim");
        userDTO.setLastName("Toft");
        userDTO.setEmail("mys@kk.gg");
        userDTO.setPassword("password123");
        userDTO.setUsername("toft");
    }

    @Test
    void registerNewUser() {
        long beforeCount = userRepository.count();
        WebsiteUser user = modelMapper.map(userDTO, WebsiteUser.class);
        userService.registerNewUser(user);

        assertEquals(beforeCount + 1, userRepository.count());
    }
}