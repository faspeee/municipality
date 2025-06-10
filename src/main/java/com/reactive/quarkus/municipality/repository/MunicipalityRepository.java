package com.reactive.quarkus.municipality.repository;

import com.reactive.quarkus.municipality.entity.Municipality;
import com.reactive.quarkus.municipality.error.Error;
import com.reactive.quarkus.municipality.error.MunicipalityNotFound;
import com.reactive.quarkus.municipality.error.MunicipalityServerError;
import com.reactive.quarkus.municipality.functional.Either;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public final class MunicipalityRepository implements PanacheRepositoryBase<Municipality, UUID> {
    /**
     * Converts a {@link Uni} of {@link Municipality} into a {@link Uni} of {@link Either}, handling nulls and errors.
     *
     * <p>If the address is found, wraps it in {@link Either.Right}. If null, wraps an {@link MunicipalityNotFound}.
     * In case of failure, wraps an {@link MunicipalityServerError}.</p>
     *
     * @param municipality the asynchronous municipality result
     * @return a {@link Uni} with either an error or a found municipality
     */
    private static Uni<Either<Error, Municipality>> processResponse(Uni<Municipality> municipality) {
        return municipality
                .<Either<Error, Municipality>>map(val -> val != null
                        ? Either.right(val)
                        : Either.left(new MunicipalityNotFound(MunicipalityRepository.class.getName())))
                .onFailure()
                .recoverWithItem(throwable -> Either.left(new MunicipalityServerError(throwable.getMessage(),
                        MunicipalityRepository.class.getName())));
    }
    /**
     * Retrieves all municipalities from the database.
     *
     * @return a {@link Uni} emitting a {@link List} of all {@link Municipality} entities
     */
    @WithTransaction
    public Uni<List<Municipality>> getAllMunicipalities() {
        return findAll().list();
    }

    /**
     * Persists or updates an {@link Municipality} and returns the result wrapped in an {@link Either}.
     *
     * <p>Delegates to {@link #processResponse(Uni)} to handle success and failure cases.</p>
     *
     * @param address the address to create or update
     * @return a {@link Uni} of {@link Either} representing success or error
     */
    @WithTransaction
    public Uni<Either<Error, Municipality>> createOrUpdateAddress(Municipality address) {
        return processResponse(persist(address));
    }
}
