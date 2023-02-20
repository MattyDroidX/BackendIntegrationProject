package com.integration.backendintegrationproject.patient;

import com.integration.backendintegrationproject.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
