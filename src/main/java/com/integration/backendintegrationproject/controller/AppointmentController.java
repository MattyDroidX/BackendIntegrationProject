package com.integration.backendintegrationproject.controller;

import com.integration.backendintegrationproject.exception.ResourceNotFoundException;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentPostDto;
import com.integration.backendintegrationproject.model.dto.Appointment.AppointmentUpdateDto;
import com.integration.backendintegrationproject.service.AppointmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController{

    private final Logger log = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppointmentDto> findAll() {
        log.info("GET method called --- Show Appointments");
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Optional<AppointmentDto>> createAppointment(@Valid @RequestBody AppointmentPostDto appointmentPostDto) {
        log.info("POST method called --- create app");
        if (appointmentPostDto == null || appointmentPostDto.patient() == null || appointmentPostDto.dentist() == null) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
        Optional<AppointmentDto> appointmentDto = service.createAppointment(appointmentPostDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDto);
    }



    @PatchMapping(value = "/{id}")
    public ResponseEntity<Optional<AppointmentDto>> updateAppointment(@Valid @RequestBody AppointmentUpdateDto appointmentUpdateDto, @PathVariable Long id) throws ResourceNotFoundException {
        log.info("Patch method called --- update Appointment");
        Optional<AppointmentDto> appointmentDto = service.updateAppointment(appointmentUpdateDto, id);
        return ResponseEntity.ok(appointmentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        log.info("DELETE method called --- delete Appointment");
        service.deleteAppointment(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
