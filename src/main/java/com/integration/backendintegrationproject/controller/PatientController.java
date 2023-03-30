package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
@Validated
public class PatientController{

    private final PatientService service;
    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll() {
        List<PatientDto> patients = service.findAll();
        log.info("Found {} patients", patients.size());
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable @NotNull Long id) {
        try {
            PatientDto patient = service.getPatientById(id);
            log.info("Found patient with id: {}", id);
            return ResponseEntity.ok(patient);
        } catch (ResourceNotFoundException e) {
            log.error("Failed to find patient with id: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@Valid @RequestBody PatientPostDto patientPostDto) {
        Optional<PatientDto> savedPatient = service.createPatient(patientPostDto);
        if (savedPatient.isPresent()) {
            log.info("Created patient with id: {}", savedPatient.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient.get());
        }
        log.error("Failed to create patient");
        return ResponseEntity.badRequest().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatientInformation(@RequestBody @Valid PatientUpdateDto patient, @PathVariable @NotNull Long id) {
        try {
            Optional<PatientDto> updatedPatient = service.updatePatientInformation(patient, id);
            if (updatedPatient.isPresent()) {
                log.info("Updated patient with id: {}", updatedPatient.get());
                return ResponseEntity.ok(updatedPatient.get());
            }
            log.error("Failed to update patient with id: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (ResourceNotFoundException e) {
            log.error("Failed to update patient with id: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable @NotNull Long id) {
        try {
            service.deletePatient(id);
            log.info("Deleted patient with id: {}", id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            log.error("Failed to delete patient with id: {}", id, e);
            return ResponseEntity.notFound().build();
        }
    }
}
