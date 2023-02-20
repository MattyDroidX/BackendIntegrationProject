package com.integration.backendintegrationproject.dentist.controller;

import com.integration.backendintegrationproject.dentist.DentistRepository;
import com.integration.backendintegrationproject.dentist.entities.Dentist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DentistController {

    private final DentistRepository dentistRepository;
    private final Logger log = LoggerFactory.getLogger(DentistController.class);

    public DentistController(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    @GetMapping("/api/dentist")
    public List<Dentist> findAll(){
        return dentistRepository.findAll();
    }

    @GetMapping("/api/dentist/{license}")
    public ResponseEntity<Dentist>findByLicense(@PathVariable Long license){
        Optional<Dentist> optDentist = dentistRepository.findById(license);
        return optDentist.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/api/dentist/{license}")
    public ResponseEntity<Dentist>modifyDentistData(@RequestBody Dentist dentist){
        if(dentist.getLicense() == null){
            log.warn("Dentist License does not exist!");
            return ResponseEntity.badRequest().build();
        }
        if(!dentistRepository.existsById(dentist.getLicense())){
            log.warn("Dentist does not exist!");
            return ResponseEntity.notFound().build();
        }
        Dentist result = dentistRepository.save(dentist);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/api/dentist")
    public Dentist addDentist(@RequestBody Dentist dentist){
        return dentistRepository.save(dentist);
    }

    @DeleteMapping("/api/dentist/{license}")
    public ResponseEntity<Dentist> deleteDentist(@PathVariable Long license){
        if(!dentistRepository.existsById(license)){
            log.warn("Dentist does not exist");
            return ResponseEntity.notFound().build();
        }
        dentistRepository.deleteById(license);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/dentist")
    public ResponseEntity<Dentist> deleteAll(){
        log.info("Request made to delete all dentist information");
        dentistRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
