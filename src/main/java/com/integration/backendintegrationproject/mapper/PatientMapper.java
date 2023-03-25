package com.integration.backendintegrationproject.mapper;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientUpdateDto;
import com.integration.backendintegrationproject.model.entities.Patient;
import org.mapstruct.*;

@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface PatientMapper {
    @Mapping( target = "id", source = "patientId" )
    PatientDto patientDto(Patient patient );

    Patient patientPostDto(PatientPostDto patient );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void patientNewUpdate(PatientUpdateDto patientUpdateDto, @MappingTarget Patient patient );

}
