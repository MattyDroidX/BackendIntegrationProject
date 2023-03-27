package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;


import java.util.List;

public interface PatientService {

    List<PatientDto> findAll();
    PatientDto createPatient(PatientPostDto patientPostDto);
    PatientDto updatePatientInformation(PatientUpdateDto patientUpdateDto, Long id)throws ResourceNotFoundException;
    void deletePatient(Long id) throws ResourceNotFoundException;

}
