package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.service.DentistService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/api/dentist")
public class DentistController{

    private final DentistService service;

    public DentistController(DentistService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DentistDto>> findAll(){
        var records = service.findAll();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("X-Total-Count", Integer.toString(records.size()));
        return new ResponseEntity(records,responseHeaders, HttpStatus.OK);
    }

    @GetMapping( value = "/{id}")
    public DentistDto getDentistById(@PathVariable Long id){
        return service.getDentistById(id);
    }
    @PostMapping
    public Optional<DentistDto> createDentist(@Valid @RequestBody DentistPostDto dentistPostDto) {
        return service.createDentist(dentistPostDto);
    }

    @PatchMapping("/{id}")
    public Optional<DentistDto> updateDentistInformation(@RequestBody DentistUpdateDto dentist, @PathVariable Long id) throws ResourceNotFoundException {
        return service.updateDentistInformation(dentist,id);
    }

    @ResponseStatus( HttpStatus.OK )
    @DeleteMapping( "/{id}" )
    public void deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        service.deleteDentist(id);
    }
}
