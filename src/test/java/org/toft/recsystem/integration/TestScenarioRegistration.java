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
import org.toft.recsystem.domain.ApiError;
import org.toft.recsystem.domain.dtos.WebsiteUserDto;

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
    private WebsiteUserDto userDto;
    private ResponseEntity<String> responseEntity;
    private WebsiteUserDto successBody;
    private ApiError errorBody;

    @BeforeAll
    void init() {
        objectMapper = new ObjectMapper().disable(MapperFeature.USE_ANNOTATIONS);
    }

    @Nested
    @DisplayName("with valid data")
    class EverythingGoesWell {
        @Test
        @Order(1)
        @DisplayName("request gets a response from the server")
        void requestGetsResponse() throws IOException {
            createNewDto();
            sendRequest(true);
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
            assertEquals(userDto.getEmail(), successBody.getEmail());
            assertEquals(userDto.getFirstName(), successBody.getFirstName());
            assertEquals(userDto.getLastName(), successBody.getLastName());
            assertEquals(userDto.getSocialSecurityNumber(), successBody.getSocialSecurityNumber());
            assertEquals(userDto.getUsername(), successBody.getUsername());
        }

        @Test
        @Order(5)
        @DisplayName("response does not include password")
        void responseDoesNotIncludePassword() {
            assertNull(successBody.getPassword());
        }
    }
    @Nested
    @DisplayName("with invalid data")
    class InvalidData {
        @Test
        @Order(6)
        @DisplayName("username")
        void userIsInvalid() throws IOException {
            createNewDto();
            userDto.setUsername("k");
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }

        @Test
        @Order(6)
        @DisplayName("firstName")
        void firstNameInvalid() throws IOException {
            createNewDto();
            userDto.setFirstName("");
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }
        @Test
        @Order(6)
        @DisplayName("email")
        void emailInvalid() throws IOException {
            createNewDto();
            userDto.setEmail("gos@...");
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }
        @Test
        @Order(6)
        @DisplayName("password")
        void passwordInvalid() throws IOException {
            createNewDto();
            userDto.setPassword("");
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }
        @Test
        @Order(6)
        @DisplayName("lastName")
        void lastNameInvalid() throws IOException {
            createNewDto();
            userDto.setLastName(null);
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }
        @Test
        @Order(6)
        @DisplayName("socialSecurityNumber")
        void socialSecurityNumberInvalid() throws IOException {
            createNewDto();
            userDto.setSocialSecurityNumber("bbb");
            sendRequest(false);
            assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }
    }

    private void createNewDto() {
        userDto =  WebsiteUserDto.builder()
                .firstName("Jan")
                .lastName("Olsson")
                .email("jo@janne.se")
                .username("janne01")
                .password("janne123")
                .socialSecurityNumber("198305119568")
                .build();
    }

    private void sendRequest(boolean expectSuccess) throws IOException {
        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String json = objectMapper.writeValueAsString(userDto);

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        String url = "http://localhost:" + port + "/api/v1/users";

        responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        if (expectSuccess) successBody = objectMapper.readValue(responseEntity.getBody(), WebsiteUserDto.class);
        else errorBody = objectMapper.readValue(responseEntity.getBody(), ApiError.class);
    }
}
