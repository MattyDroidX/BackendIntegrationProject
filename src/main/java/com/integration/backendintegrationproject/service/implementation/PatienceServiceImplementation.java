package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.mapper.PatientMapper;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.repository.PatientRepository;
import com.integration.backendintegrationproject.service.PatientService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatienceServiceImplementation implements PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;


    @Override
    public List<PatientDto> findAll() {
        var patients = repository.findAll();
        return patients.stream()
                .map( mapper::patientDto )
                .toList();
    }

    @Override
    public PatientDto createDentist(PatientPostDto patient) {
        var saved = repository.save( mapper.patientPostDto( patient ));
        return mapper.patientDto( saved );
    }

    @Override
    public PatientDto updateDentistInformation(PatientUpdateDto patientUpdateDto, Long id) throws ResourceNotFoundException {
        var patients = repository.findById( id ).orElseThrow( ResourceNotFoundException::new );
        mapper.patientNewUpdate( patientUpdateDto, patients );
        return mapper.patientDto(repository.save( patients ) );
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
