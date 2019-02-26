package org.toft.recsystem.integration;


import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.toft.recsystem.RecsystemApplication;
import org.toft.recsystem.domain.dtos.UserDTO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RecsystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Scenario: registration")
class TestScenarioRegistration {
    @LocalServerPort
    private int port;
    private ObjectMapper objectMapper;
    private UserDTO userDTO;
    private ResponseEntity<String> responseEntity;
    private UserDTO responseDTO;

    @BeforeAll
    void init() {
        objectMapper = new ObjectMapper().disable(MapperFeature.USE_ANNOTATIONS);
        userDTO = UserDTO.builder()
                .firstName("Jan")
                .lastName("Olsson")
                .email("jo@janne.se")
                .username("janne")
                .password("janne123")
                .socialSecurityNumber("843295211")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("request gets a response from the server")
    void requestGetsResponse() throws IOException {
        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String json = objectMapper.writeValueAsString(userDTO);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        String url = "http://localhost:" + port + "/api/v1/users";

        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        responseDTO = objectMapper.readValue(responseEntity.getBody(), UserDTO.class);
    }

    @Test
    @Order(2)
    @DisplayName("response has correct status code (501)")
    void responseHasCorrectStatusCode() {
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("response has correct content type header")
    void hasCorrectHeaders() {
        assertEquals(responseEntity.getHeaders().getContentType(), MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    @Order(4)
    @DisplayName("response returns the user object on successful creation")
    void includesUserInformationInResponse() {
        assertEquals(userDTO.getEmail(), responseDTO.getEmail());
        assertEquals(userDTO.getFirstName(), responseDTO.getFirstName());
        assertEquals(userDTO.getLastName(), responseDTO.getLastName());
        assertEquals(userDTO.getSocialSecurityNumber(), responseDTO.getSocialSecurityNumber());
        assertEquals(userDTO.getUsername(), responseDTO.getUsername());
    }

    @Test
    @Order(5)
    @DisplayName("response does not include password")
    void responseDoesNotIncludePassword() {
        assertNull(responseDTO.getPassword());
    }
}
