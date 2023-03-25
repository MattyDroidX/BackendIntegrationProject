package com.integration.backendintegrationproject.repository;

import com.integration.backendintegrationproject.model.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
