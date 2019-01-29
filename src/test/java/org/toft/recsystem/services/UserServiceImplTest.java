package org.toft.recsystem.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.toft.recsystem.domain.UserDTO;
import org.toft.recsystem.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserRepository userRepository;
    private UserDTO userDTO;

    @BeforeAll
    void setupUserDTO() {
        userDTO = new UserDTO();

        userDTO.setUsername("Joachim");
        userDTO.setLastName("Toft");
        userDTO.setEmail("mys@kk.gg");
        userDTO.setPassword("password123");
        userDTO.setUsername("toft");
    }

    @Test
    void registerNewUser() {
        long beforeCount = userRepository.count();

        userService.registerNewUser(userDTO);

        assertEquals(beforeCount + 1, userRepository.count());
    }
}