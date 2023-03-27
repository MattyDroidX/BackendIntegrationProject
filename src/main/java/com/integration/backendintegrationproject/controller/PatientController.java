package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/patient")
public class PatientController{

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public PatientDto createPatient(@Valid @RequestBody PatientPostDto patientPostDto) {
        return service.createPatient(patientPostDto);
    }

    @PatchMapping("/{id}")
    public PatientDto updatePatientInformation(@RequestBody PatientUpdateDto patient,@PathVariable Long id) throws ResourceNotFoundException {
        return service.updatePatientInformation(patient, id);
    }

    @ResponseStatus( HttpStatus.OK )
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        service.deletePatient(id);
    }
}
