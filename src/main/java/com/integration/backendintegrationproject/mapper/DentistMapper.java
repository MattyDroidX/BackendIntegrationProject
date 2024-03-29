package com.integration.backendintegrationproject.mapper;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.model.entities.Dentist;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper( componentModel = "spring")
public interface DentistMapper {
    DentistDto dentistDto(Dentist dentist );
    DentistDto dentistDtoToDentist(Dentist dentist);
    Dentist dentistToPostDto(DentistPostDto dentist );
    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void dentistNewUpdateDto(DentistUpdateDto dentistUpdateDto, @MappingTarget Dentist dentist );
}
