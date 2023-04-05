package com.integration.backendintegrationproject.service;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentPostDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Optional<AppointmentDto> createAppointment(AppointmentPostDto appointmentPostDto );

    List<AppointmentDto>findAll();

    Optional<AppointmentDto> updateAppointment(AppointmentUpdateDto appointmentUpdateDto, Long id );

    void deleteAppointment(Long id) throws ResourceNotFoundException;

}
