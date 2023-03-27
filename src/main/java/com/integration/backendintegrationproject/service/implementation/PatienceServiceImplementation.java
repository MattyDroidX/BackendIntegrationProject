package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.mapper.PatientMapper;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.model.entities.Patient;
import com.integration.backendintegrationproject.repository.PatientRepository;
import com.integration.backendintegrationproject.service.PatientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatienceServiceImplementation implements PatientService {
    private final PatientRepository repository;
    private final PatientMapper mapper;

    public PatienceServiceImplementation(PatientRepository repository, PatientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<PatientDto> findAll() {
        var patients = repository.findAll();
        return patients.stream()
                .map(mapper::patientDto)
                .toList();
    }

//        @Override
//    public PatientDto createPatient(PatientPostDto patientPostDto) {
//        var saved = repository.save( new Patient(
//                patientPostDto.name(),
//                patientPostDto.surname(),
//                patientPostDto.address(),
//                patientPostDto.DNI(),
//                patientPostDto.appointmentDate()));
//        return new PatientDto(
//                saved.getName(),
//                saved.getSurname(),
//                saved.getAddress(),
//                saved.getDNI(),
//                saved.getAppointmentDate());
//    }
    @Override
    public PatientDto createPatient(PatientPostDto patientPostDto) {
        var saved = repository.save(mapper.patientPostDto(patientPostDto));
        return mapper.PatientPostDto(saved);
    }

    @Override
    public PatientDto updatePatientInformation(PatientUpdateDto patientUpdateDto, Long id) throws ResourceNotFoundException {
        var patients = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        mapper.patientNewUpdate(patientUpdateDto, patients);
        return mapper.patientDto(repository.save(patients));
    }

    @Override
    public void deletePatient(Long id) throws ResourceNotFoundException {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }
}
