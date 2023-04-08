package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentDto;
import com.integration.backendintegrationproject.model.entities.Appointment;
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
class AppointmentControllerTest {
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

    @DisplayName("Testing Http GET method for all appointments")
    @Test
    void findAll() {
        ResponseEntity<List> response =
                testRestTemplate.getForEntity("/api/appointment", List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @DisplayName("Testing http GET to reach one appointment")
    @Test
    void findById() {
        ResponseEntity<Appointment> response =
                testRestTemplate.getForEntity("/api/appointment/1", Appointment.class);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    }
    @DisplayName("Testing http POST to add a new appointment")
    @Test
    void addAppointment() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        String json= """
            {
              "dentist_id": 1L,
              "patient_id": 1L
            }
            """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<AppointmentDto> response =
                testRestTemplate.exchange("/api/appointment",HttpMethod.POST, request, AppointmentDto.class);
        AppointmentDto result = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(result);
        assertEquals(null, result.dentist_id());
        assertEquals(null, result.patient_id());
    }

}