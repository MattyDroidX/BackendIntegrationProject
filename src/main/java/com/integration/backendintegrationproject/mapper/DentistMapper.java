package com.integration.backendintegrationproject.mapper;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientDto;
import com.integration.backendintegrationproject.model.dto.Patient.PatientPostDto;
import com.integration.backendintegrationproject.model.entities.Dentist;
import com.integration.backendintegrationproject.model.entities.Patient;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper( componentModel = "spring")
public interface DentistMapper {
    DentistDto dentistDto(Dentist dentist );

    DentistDto DentistPostDto(Dentist dentist);
    Dentist dentistPostDto(DentistPostDto dentist );




    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void dentistNewUpdateDto(DentistUpdateDto dentistUpdateDto, @MappingTarget Dentist dentist );

}
