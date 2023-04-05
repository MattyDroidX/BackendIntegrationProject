package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;


import java.util.List;
import java.util.Optional;

public interface DentistService {

    List<DentistDto> findAll();
    DentistDto getDentistById( Long id ) throws ResourceNotFoundException;
    Optional<DentistDto> createDentist(DentistPostDto dentist);

    Optional<DentistDto> updateDentistInformation(DentistUpdateDto dentist, Long id)throws ResourceNotFoundException;
    void deleteDentist(Long id) throws ResourceNotFoundException;

}
