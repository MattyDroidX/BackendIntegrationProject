package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.mapper.PatientMapper;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.repository.PatientRepository;
import com.integration.backendintegrationproject.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatienceServiceImplementation implements PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final Logger log = LoggerFactory.getLogger(DentistServiceImplementation.class);


    public PatienceServiceImplementation(PatientRepository repository, @Qualifier("patientMapperImpl") PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<PatientDto> findAll() {
        var patients = repository.findAll();
        log.info("Found {} patients in the database.", patients.size());
        return patients.stream()
                .map(mapper::patientDto)
                .toList();
    }

    @Override
    public PatientDto getPatientById(Long id) throws ResourceNotFoundException {
        var patient = repository.findById( id )
                .orElseThrow(() -> {
                    log.error("Dentist with id {} not found.", id);
                    return new ResourceNotFoundException("Patient not found");
                });
        log.info("Retrieved information for patient with id {}.", id);
        return mapper.patientDto( patient );
    }

    @Override
    public Optional<PatientDto> createPatient(PatientPostDto patientPostDto) {
        var saved = repository.save(mapper.patientToPostDto(patientPostDto));
        log.info("Created a new patient with id {}.", saved.getId());
        return Optional.ofNullable(mapper.patientPostDtoToPatient(saved));
    }

    @Override
    public Optional<PatientDto> updatePatientInformation(PatientUpdateDto patientUpdateDto, Long id) throws ResourceNotFoundException {
        var patients = repository.findById( id ).orElseThrow(() -> {
            log.error("Patient with id {} not found.", id);
            return new ResourceNotFoundException("Patient not found");
        });
        mapper.patientNewUpdate( patientUpdateDto, patients );
        log.info("Updated information for dentist with id {}.", id);
        return Optional.ofNullable(mapper.patientDto(repository.save(patients)));
    }

    @Override
    public void deletePatient(Long id) throws ResourceNotFoundException {
        try {
            repository.deleteById( id );
            log.info("Deleted information for patient with id {}.", id);
        } catch ( EmptyResultDataAccessException exception ) {
            log.error("Patient with id {} not found.", id);
            throw new ResourceNotFoundException( exception.getMessage() );
        }
    }
}
