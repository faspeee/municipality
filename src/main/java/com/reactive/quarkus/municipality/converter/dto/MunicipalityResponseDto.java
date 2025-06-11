package com.reactive.quarkus.municipality.converter.dto;

public record MunicipalityResponseDto(String regionCode, String provinceCode, String municipalityCode,
                                      String municipalitySigle, String municipalityName, String regionName,
                                      String cadastralCode, String territorialUnitType, String capitalsMunicipality,
                                      double latitude, double longitude, double altitude) {
}
