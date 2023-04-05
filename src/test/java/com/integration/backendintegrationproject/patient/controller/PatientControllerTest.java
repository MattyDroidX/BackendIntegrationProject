package com.integration.backendintegrationproject.patient.controller;

import com.integration.backendintegrationproject.model.entities.Patient;
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
class PatientControllerTest {
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port).basicAuthentication("admin","password");
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Testing Http GET method for all patients")
    @Test
    void findAll() {
        ResponseEntity<List> response =
                testRestTemplate.getForEntity("/api/patient", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @DisplayName("Testing http GET to reach one patient")
    @Test
    void findById() {
        ResponseEntity<Patient> response =
                testRestTemplate.getForEntity("/api/patient/1", Patient.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @DisplayName("Testing http POST to add a new patient")
    @Test
    void addPatient() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json= """
                {
                  "name": "Cosme",
                  "surname": "Fulanito"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Patient> response =
                testRestTemplate.exchange("/api/patient",HttpMethod.POST, request, Patient.class);
        Patient result = response.getBody();

        assert result != null;
        assertEquals("Fulanito", result.getSurname());
        assertEquals("Cosme", result.getName());
    }
}