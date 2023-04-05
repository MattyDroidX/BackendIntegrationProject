package com.integration.backendintegrationproject.repository;

import com.integration.backendintegrationproject.model.entities.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist,Long> {
}
