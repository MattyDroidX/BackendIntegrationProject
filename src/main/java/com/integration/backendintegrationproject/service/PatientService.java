package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;


import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<PatientDto> findAll();
    PatientDto getPatientById(Long id ) throws ResourceNotFoundException;
    Optional<PatientDto> createPatient(PatientPostDto patientPostDto);
    Optional<PatientDto> updatePatientInformation(PatientUpdateDto patientUpdateDto, Long id)throws ResourceNotFoundException;
    void deletePatient(Long id) throws ResourceNotFoundException;

}
