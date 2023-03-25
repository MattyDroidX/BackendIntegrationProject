package com.integration.backendintegrationproject.mapper;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistPostDto;
import com.integration.backendintegrationproject.model.dto.Dentist.DentistUpdateDto;
import com.integration.backendintegrationproject.model.entities.Dentist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface DentistMapper {
    @Mapping( target = "id", source = "dentistId" )
    DentistDto dentistDto(Dentist dentist );

    Dentist dentistPostDto(DentistPostDto dentist );

    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    void dentistNewUpdateDto(DentistUpdateDto dentistUpdateDto, @MappingTarget Dentist dentist );

}
