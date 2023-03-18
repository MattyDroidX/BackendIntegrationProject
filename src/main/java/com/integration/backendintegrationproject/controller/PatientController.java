package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.repository.PatientRepository;
import com.integration.backendintegrationproject.model.entities.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    private final PatientRepository patientRepository;
    private final Logger log = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/api/patient")
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    @GetMapping("/api/patient/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id){
        Optional<Patient> optPatient = patientRepository.findById(id);
        return optPatient.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/api/patient/{id}")
    public ResponseEntity<Patient>modifyPatientData(@RequestBody Patient patient){
        if(patient.getId() == null){
            log.warn("Patient id does not exist!");
            return ResponseEntity.badRequest().build();
        }
        if(!patientRepository.existsById(patient.getId())){
            log.warn("Patient does not exist!");
            return ResponseEntity.notFound().build();
        }
        Patient result = patientRepository.save(patient);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/patient")
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }

    @DeleteMapping("/api/patient/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id){
        if(!patientRepository.existsById(id)){
            log.warn("Patient does not exist");
            return ResponseEntity.notFound().build();
        }
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/patient")
    public ResponseEntity<Patient> deleteAll(){
        log.info("Request made to delete all patients information");
        patientRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
