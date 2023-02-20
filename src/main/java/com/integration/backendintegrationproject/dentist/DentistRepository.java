package com.integration.backendintegrationproject.dentist;

import com.integration.backendintegrationproject.dentist.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist,Long> {
}
