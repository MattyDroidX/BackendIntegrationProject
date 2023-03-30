package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.mapper.DentistMapper;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.repository.DentistRepository;
import com.integration.backendintegrationproject.service.DentistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistServiceImplementation implements DentistService {

    private final DentistRepository repository;
    private final DentistMapper mapper;
    private final Logger log = LoggerFactory.getLogger(DentistServiceImplementation.class);

    public DentistServiceImplementation(DentistRepository repository, @Qualifier("dentistMapperImpl") DentistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DentistDto> findAll() {
        var dentists = repository.findAll();
        log.info("Found {} dentists in the database.", dentists.size());
        return dentists.stream()
                .map( mapper::dentistDto )
                .toList();
    }

    @Override
    public DentistDto getDentistById(Long id) throws ResourceNotFoundException {
        var dentist = repository.findById( id )
                .orElseThrow(() -> {
                    log.error("Dentist with id {} not found.", id);
                    return new ResourceNotFoundException("Dentist not found");
                });
        log.info("Retrieved information for dentist with id {}.", id);
        return mapper.dentistDto( dentist );
    }

    @Override
    public Optional<DentistDto> createDentist(DentistPostDto dentistPostDto) {
        var saved = repository.save( mapper.dentistToPostDto( dentistPostDto ));
        log.info("Created a new dentist with id {}.", saved.getId());
        return Optional.ofNullable(mapper.dentistDtoToDentist(saved));
    }

    @Override
    public Optional<DentistDto> updateDentistInformation(DentistUpdateDto dentist, Long id) throws ResourceNotFoundException {
        var dentists = repository.findById( id ).orElseThrow(() -> {
            log.error("Dentist with id {} not found.", id);
            return new ResourceNotFoundException("Dentist not found");
        });
        mapper.dentistNewUpdateDto( dentist, dentists );
        log.info("Updated information for dentist with id {}.", id);
        return Optional.ofNullable(mapper.dentistDto(repository.save(dentists)));
    }

    @Override
    public void deleteDentist(Long id) throws ResourceNotFoundException {
        try {
            repository.deleteById( id );
            log.info("Deleted information for dentist with id {}.", id);
        } catch ( EmptyResultDataAccessException exception ) {
            log.error("Dentist with id {} not found.", id);
            throw new ResourceNotFoundException( exception.getMessage() );
        }
    }
}
