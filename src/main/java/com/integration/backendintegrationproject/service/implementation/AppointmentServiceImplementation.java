package com.integration.backendintegrationproject.service.implementation;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentPostDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentUpdateDto;
import com.integration.backendintegrationproject.model.entities.Appointment;
import com.integration.backendintegrationproject.repository.AppointmentRepository;
import com.integration.backendintegrationproject.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    private final Logger log = LoggerFactory.getLogger(AppointmentServiceImplementation.class);
    private final AppointmentRepository repository;


    public AppointmentServiceImplementation(AppointmentRepository repository) {
        this.repository = repository;
    }

    private AppointmentDto toDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getDentistAppointment().getId(),
                appointment.getPatientAppointment().getId(),
                appointment.getRegistrationDate()
        );
    }

    private void validateAppointmentPostDto(AppointmentPostDto appointmentPostDto) {
        if (Objects.isNull(appointmentPostDto) || Objects.isNull(appointmentPostDto.dentist())
                || Objects.isNull(appointmentPostDto.patient())) {
            log.error("Appointment post request is invalid.");
            throw new ResourceNotFoundException("Appointment post request is invalid.");
        }
    }


    private void updateAppointmentFields(Appointment appointment, AppointmentUpdateDto appointmentUpdateDto) {
        if (Objects.nonNull(appointmentUpdateDto.dentist())) {
            appointment.setDentistAppointment(appointmentUpdateDto.dentist());
        }
        if (Objects.nonNull(appointmentUpdateDto.patient())) {
            appointment.setPatientAppointment(appointmentUpdateDto.patient());
        }
        if (Objects.nonNull(appointmentUpdateDto.registrationDate())) {
            appointment.setRegistrationDate(appointmentUpdateDto.registrationDate());
        }
    }

    @Override
    public Optional<AppointmentDto> createAppointment(AppointmentPostDto appointmentPostDto) {
        validateAppointmentPostDto(appointmentPostDto);
        var saved = repository.save(new Appointment(
                null,
                appointmentPostDto.dentist(),
                appointmentPostDto.patient(),
                appointmentPostDto.registrationDate()
        ));
        log.info("Appointment created successfully with ID: {}", saved.getId());
        return Optional.of(toDto(saved));
    }

    @Override
    public List<AppointmentDto> findAll() {
        var appointments = repository.findAll();
        return appointments.stream().map(this::toDto).toList();
    }

    @Override
    public Optional<AppointmentDto> updateAppointment(AppointmentUpdateDto appointmentUpdateDto, Long id) {
        var appointment = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Appointment not found with id: {}", id);
                    return new ResourceNotFoundException("Appointment not found with id " + id);
                });

        updateAppointmentFields(appointment, appointmentUpdateDto);
        var updatedAppointment = repository.save(appointment);
        log.info("Appointment updated successfully with ID: {}", updatedAppointment.getId());
        return Optional.of(toDto(updatedAppointment));
    }

    @Override
    public void deleteAppointment(Long id) throws ResourceNotFoundException {
        try {
            repository.deleteById(id);
            log.info("Appointment deleted successfully with ID: {}", id);
        } catch (EmptyResultDataAccessException exception) {
            log.error("Appointment not found with id: {}", id);
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }
}