package com.integration.backendintegrationproject.dentist.controller;

import com.integration.backendintegrationproject.model.entities.Dentist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class DentistControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Testing Http GET method for all dentist")
    @Test
    void findAll() {
        ResponseEntity<List> response =
                testRestTemplate.getForEntity("/api/dentist", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @DisplayName("Testing http GET to reach one dentist")
    @Test
    void findById() {
        ResponseEntity<Dentist> response =
                testRestTemplate.getForEntity("/api/dentist/1", Dentist.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @DisplayName("Testing http POST to add a new dentist")
    @Test
    void addPatient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json= """
                {
                  "name": "Jack",
                  "lastName": "Sparrow"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Dentist> response =
                testRestTemplate.exchange("/api/dentist",HttpMethod.POST, request, Dentist.class);
        Dentist result = response.getBody();

        assert result != null;
        assertEquals("Sparrow", result.getLastName());
        assertEquals("Jack", result.getName());
    }

}