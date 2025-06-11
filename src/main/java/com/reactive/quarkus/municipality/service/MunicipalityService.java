package com.reactive.quarkus.municipality.service;

import com.reactive.quarkus.municipality.converter.MunicipalityConverter;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityResponseDto;
import com.reactive.quarkus.municipality.converter.dto.SaveMunicipalityResponseDto;
import com.reactive.quarkus.municipality.error.Error;
import com.reactive.quarkus.municipality.functional.Either;
import com.reactive.quarkus.municipality.repository.MunicipalityRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public final class MunicipalityService {
    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityConverter municipalityConverter;
    public MunicipalityService(MunicipalityRepository municipalityRepository, MunicipalityConverter municipalityConverter) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityConverter = municipalityConverter;
    }

    public Multi<Set<MunicipalityResponseDto>> getAllMunicipalities() {
        return municipalityRepository.getAllMunicipalities().toMulti()
                .map(municipalities -> municipalities.stream()
                        .map(municipalityConverter::toDto)
                        .collect(Collectors.toSet()));
    }

    public Uni<Either<Error, SaveMunicipalityResponseDto>> createMunicipality(MunicipalityRequestDto municipalityRequestDto) {

    }
}
