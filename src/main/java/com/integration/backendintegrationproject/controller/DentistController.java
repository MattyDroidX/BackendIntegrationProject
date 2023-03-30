package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.service.DentistService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/dentist")
public class DentistController{

    private final DentistService service;

    public DentistController(DentistService service) {
        this.service = service;
    }

    @GetMapping
    public List<DentistDto> findAll(){
        return service.findAll();
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
