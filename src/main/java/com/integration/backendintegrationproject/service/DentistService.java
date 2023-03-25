package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.model.entities.Dentist;


import java.util.List;

public interface DentistService {

    List<DentistDto> findAll();
    DentistDto createDentist(DentistPostDto dentist);
    DentistDto updateDentistInformation(DentistUpdateDto dentist, Long license)throws ResourceNotFoundException;
    void deleteDentist(Long id) throws ResourceNotFoundException;

}
