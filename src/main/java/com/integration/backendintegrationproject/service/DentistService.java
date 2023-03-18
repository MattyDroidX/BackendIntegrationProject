package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.model.entities.Dentist;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface DentistService {

    List<Dentist> findAll();
    ResponseEntity<Dentist>findByLicense(Long license);
    ResponseEntity<Dentist>modifyDentistData(Dentist dentist);

}
