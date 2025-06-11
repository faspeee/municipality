package com.reactive.quarkus.municipality.converter;

import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;
import com.reactive.quarkus.municipality.converter.dto.SaveMunicipalityResponseDto;
import com.reactive.quarkus.municipality.entity.Municipality;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public final class MunicipalitySaveConverter implements SaveConverter<MunicipalityRequestDto, SaveMunicipalityResponseDto,Municipality>{

    @Override
    public SaveMunicipalityResponseDto toDto(String message) {
        return new SaveMunicipalityResponseDto(message);
    }

    @Override
    public Municipality toEntity(MunicipalityRequestDto model) {
        Municipality entity = new Municipality();
        entity.setAltitude(model.altitude());
        entity.setLatitude(model.latitude());
        entity.setLongitude(model.longitude());
        entity.setCapitalsMunicipality(model.capitalsMunicipality());
        entity.setCadastralCode(model.cadastralCode());
        entity.setMunicipalityCode(model.municipalityCode());
        entity.setMunicipalityName(model.municipalityName());
        entity.setMunicipalitySigle(model.municipalitySigle());
        entity.setProvinceCode(model.provinceCode());
        entity.setRegionCode(model.regionCode());
        entity.setRegionName(model.regionName());
        entity.setTerritorialUnitType(model.territorialUnitType());
        return entity;
    }
}
