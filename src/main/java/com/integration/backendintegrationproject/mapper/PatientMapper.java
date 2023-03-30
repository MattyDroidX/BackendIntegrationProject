package com.integration.backendintegrationproject.mapper;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.model.entities.Patient;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper( componentModel = "spring")
public interface PatientMapper {
    PatientDto patientDto(Patient patient );

    PatientDto patientPostDtoToPatient(Patient patient);
    Patient patientToPostDto(PatientPostDto patient );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void patientNewUpdate(PatientUpdateDto patientUpdateDto, @MappingTarget Patient patient );

}
