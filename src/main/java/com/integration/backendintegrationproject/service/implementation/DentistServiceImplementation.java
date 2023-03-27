package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.mapper.DentistMapper;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.repository.DentistRepository;
import com.integration.backendintegrationproject.service.DentistService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImplementation implements DentistService {

    private final DentistRepository repository;
    private final DentistMapper mapper;

    public DentistServiceImplementation(DentistRepository repository,DentistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DentistDto> findAll() {
        var dentists = repository.findAll();
        return dentists.stream()
                .map( mapper::dentistDto )
                .toList();
    }

    @Override
    public DentistDto createDentist(DentistPostDto dentistPostDto) {
        var saved = repository.save( mapper.dentistPostDto( dentistPostDto ));
        return mapper.DentistPostDto( saved );
    }

    @Override
    public DentistDto updateDentistInformation(DentistUpdateDto dentist, Long id) throws ResourceNotFoundException {
        var dentists = repository.findById( id ).orElseThrow( ResourceNotFoundException::new );
        mapper.dentistNewUpdateDto( dentist, dentists );
        return mapper.dentistDto(repository.save( dentists ) );
    }

    @Override
    public void deleteDentist(Long id) throws ResourceNotFoundException {
        try {
            repository.deleteById( id );
        } catch ( EmptyResultDataAccessException exception ) {
            throw new ResourceNotFoundException( exception.getMessage() );
        }
    }
}
