package com.reactive.quarkus.municipality.service;

import com.reactive.quarkus.municipality.converter.MunicipalityConverter;
import com.reactive.quarkus.municipality.converter.MunicipalitySaveConverter;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityRequestDto;
import com.reactive.quarkus.municipality.converter.dto.MunicipalityResponseDto;
import com.reactive.quarkus.municipality.converter.dto.SaveMunicipalityResponseDto;
import com.reactive.quarkus.municipality.error.Error;
import com.reactive.quarkus.municipality.error.MunicipalityServerError;
import com.reactive.quarkus.municipality.functional.Either;
import com.reactive.quarkus.municipality.repository.MunicipalityRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application-scoped service for handling business logic related to municipalities.
 *
 * <p>This service coordinates the retrieval and creation of municipalities by delegating to:
 * <ul>
 *   <li>{@link MunicipalityRepository} for data persistence operations,</li>
 *   <li>{@link MunicipalityConverter} for transforming entities to output DTOs,</li>
 *   <li>{@link MunicipalitySaveConverter} for mapping input DTOs to entities and producing output for creation responses.</li>
 * </ul>
 *
 * <p>This service uses Mutiny's {@link Uni} and {@link Multi} for asynchronous and reactive operations.
 * It also embraces functional error handling using Vavr's {@link Either}, allowing error or success propagation through the response pipeline.
 */
@ApplicationScoped
public final class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityConverter municipalityConverter;
    private final MunicipalitySaveConverter municipalitySaveConverter;

    /**
     * Constructs a new instance of {@code MunicipalityService}.
     *
     * @param municipalityRepository       the repository used for accessing municipality data; must not be {@code null}
     * @param municipalityConverter        the converter for transforming entities to response DTOs; must not be {@code null}
     * @param municipalitySaveConverter    the converter for mapping request DTOs to entities and vice versa; must not be {@code null}
     */
    public MunicipalityService(MunicipalityRepository municipalityRepository,
                               MunicipalityConverter municipalityConverter,
                               MunicipalitySaveConverter municipalitySaveConverter) {
        this.municipalityRepository = municipalityRepository;
        this.municipalityConverter = municipalityConverter;
        this.municipalitySaveConverter = municipalitySaveConverter;
    }

    /**
     * Retrieves all municipalities from the data source and converts them into a set of response DTOs.
     *
     * <p>The method returns a {@link Multi} that emits a single set of {@link MunicipalityResponseDto}s,
     * providing a non-blocking and reactive interface suitable for streaming or reactive chaining.
     *
     * @return a {@code Multi} emitting a single set of {@code MunicipalityResponseDto} objects
     */
    public Multi<Set<MunicipalityResponseDto>> getAllMunicipalities() {
        return municipalityRepository.getAllMunicipalities().toMulti()
                .map(municipalities -> municipalities.stream()
                        .map(municipalityConverter::toDto)
                        .collect(Collectors.toSet()));
    }

    /**
     * Creates or updates a municipality in the data source based on the provided request DTO.
     *
     * <p>The method performs the following steps:
     * <ol>
     *   <li>Converts the input {@link MunicipalityRequestDto} into an entity.</li>
     *   <li>Invokes the repository to persist the entity.</li>
     *   <li>Handles any failure by wrapping it into a domain-specific {@link MunicipalityServerError}.</li>
     *   <li>If successful, maps the result to a {@link SaveMunicipalityResponseDto} with a static success message.</li>
     * </ol>
     *
     * <p>The result is wrapped in a {@link Uni} and returned as an {@link Either}, where:
     * <ul>
     *   <li>{@code Left<Error>} represents a failure case,</li>
     *   <li>{@code Right<SaveMunicipalityResponseDto>} represents a successful creation.</li>
     * </ul>
     *
     * @param municipalityRequestDto the DTO containing municipality creation data; must not be {@code null}
     * @return a {@code Uni} containing an {@code Either} with error or success response
     */
    public Uni<Either<Error, SaveMunicipalityResponseDto>> createMunicipality(MunicipalityRequestDto municipalityRequestDto) {
        return municipalityRepository.createOrUpdateAddress(municipalitySaveConverter.toEntity(municipalityRequestDto))
                .onFailure()
                .recoverWithItem(throwable ->
                        Either.left(new MunicipalityServerError(
                                throwable.getMessage(),
                                MunicipalityService.class.getName())))
                .map(errorMunicipalityEither ->
                        errorMunicipalityEither.map(ignored ->
                                municipalitySaveConverter.toDto("municipality creation is ok")));
    }
}
