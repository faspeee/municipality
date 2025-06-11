package com.reactive.quarkus.municipality.converter.dto;

import jakarta.validation.constraints.NotEmpty;

public record MunicipalityRequestDto(@NotEmpty(message = "the region code is mandatory") String regionCode,
                                     @NotEmpty(message = "the province code is mandatory") String provinceCode,
                                     @NotEmpty(message = "the municipality code is mandatory") String municipalityCode,
                                     String municipalitySigle,
                                     @NotEmpty(message = "the municipality name is mandatory") String municipalityName,
                                     @NotEmpty(message = "the region name is mandatory") String regionName,
                                     String cadastralCode, String territorialUnitType, String capitalsMunicipality,
                                     double latitude, double longitude, double altitude) {
}
