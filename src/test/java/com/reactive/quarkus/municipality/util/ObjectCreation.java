package com.reactive.quarkus.municipality.util;

import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;

import java.util.function.Supplier;

public final class ObjectCreation {
    public static final Supplier<MunicipalityRequestDto> createFlorence = () -> createMunicipalityRequestDto(
            "09",               // regionCode (Tuscany)
            "048",              // provinceCode (Firenze)
            "48017",            // municipalityCode
            "017",              // municipalitySigle
            "Firenze",          // municipalityName
            "Toscana",          // regionName
            "D612",             // cadastralCode (official cadastral code for Firenze)
            "048",              // territorialUnitType
            "Firenze",          // capitalsMunicipality
            43.7695604,         // latitude
            11.2558136,         // longitude
            50.0                // altitude (approximate)
    );
    public static final Supplier<MunicipalityRequestDto> createBariError = () -> createMunicipalityRequestDto(
            "16",               // regionCode (Puglia)
            "072",              // provinceCode (Bari)
            "72006",            // municipalityCode
            "006",              // municipalitySigle
            null,             // municipalityName
            "Puglia",           // regionName
            "A662",             // cadastralCode (official for Bari)
            "072",              // territorialUnitType
            "Bari",             // capitalsMunicipality
            41.1171432,         // latitude
            16.8718715,         // longitude
            5.0                 // altitude (approximate for coastal area)
    );

    private ObjectCreation() {
    }

    private static MunicipalityRequestDto createMunicipalityRequestDto(String regionCode, String provinceCode, String municipalityCode,
                                                                       String municipalitySigle, String municipalityName, String regionName,
                                                                       String cadastralCode, String territorialUnitType, String capitalsMunicipality,
                                                                       double latitude, double longitude, double altitude) {
        return new MunicipalityRequestDto(regionCode, provinceCode, municipalityCode, municipalitySigle, municipalityName,
                regionName, cadastralCode, territorialUnitType, capitalsMunicipality, latitude, longitude, altitude);
    }
}
