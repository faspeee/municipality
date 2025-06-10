package com.reactive.quarkus.municipality.converter;

import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityResponseDto;
import com.reactive.quarkus.municipality.entity.Municipality;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class MunicipalityConverter implements Converter<MunicipalityRequestDto, MunicipalityResponseDto,Municipality>{

    @Override
    public MunicipalityResponseDto toDto(Municipality entity) {
        return null;
    }

    @Override
    public Municipality toEntity(MunicipalityRequestDto model) {
        return null;
    }
}
