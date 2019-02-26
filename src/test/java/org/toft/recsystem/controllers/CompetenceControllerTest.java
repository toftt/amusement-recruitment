package org.toft.recsystem.controllers;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.toft.recsystem.RecsystemApplication;
import org.toft.recsystem.security.TestSecurityUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RecsystemApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CompetenceControllerTest {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void getAllCompetences() throws JSONException {
        HttpHeaders headers = new HttpHeaders();

        String token = TestSecurityUtil.createToken();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("api/v1/competences"),
                HttpMethod.GET, entity, String.class);

        String expected = "[{name:\"Hot Dog Bbq\"},{name:\"Rollerblades\"}]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}