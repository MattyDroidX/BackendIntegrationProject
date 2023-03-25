package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.service.PatientService;

import java.util.List;

public class PatienceServiceImplementation implements PatientService {
    @Override
    public List<PatientDto> findAll() {
        return null;
    }

    @Override
    public PatientDto createDentist(PatientPostDto patient) {
        return null;
    }

    @Override
    public PatientDto updateDentistInformation(PatientUpdateDto patient, Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteDentist(Long id) throws ResourceNotFoundException {

    }
}
