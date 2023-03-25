package com.integration.backendintegrationproject.config;

import com.integration.backendintegrationproject.mapper.DentistMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Mapper(componentModel = "spring")
public class MapperConfig {

    @Bean
    public DentistMapper dentistMapper() {
        return Mappers.getMapper(DentistMapper.class);
    }
}