package com.reactive.quarkus.municipality.converter;


import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generic interface for converting between data used during a "save" operation:
 * input DTOs, domain entities, and result/output DTOs based on a message.
 *
 * <p>This converter is typically used in service layers where:
 * <ul>
 *   <li>{@code DI} is the input DTO (e.g., a request body from the client),</li>
 *   <li>{@code DO} is the output DTO (e.g., a response after a save operation),</li>
 *   <li>{@code E} is the internal entity model (e.g., JPA entity or domain object).</li>
 * </ul>
 *
 * <p>Unlike a general-purpose converter, this interface assumes that the output DTO is created
 * from a status {@code String} message after the save operation.
 *
 * @param <DI> the type of input DTO used to build the entity
 * @param <DO> the type of output DTO built from a result message
 * @param <E>  the type of entity that represents the internal domain or persistence model
 */
public interface SaveConverter<DI, DO, E> {

    /**
     * Converts a result message into an output DTO.
     *
     * <p>This method is typically used to communicate the result of a save or update operation,
     * such as confirmation messages or operation status.
     *
     * @param message the message indicating the result of the save operation; must not be {@code null}
     * @return an output DTO constructed from the given message
     * @throws NullPointerException if {@code message} is {@code null}
     */
    DO toDto(String message);

    /**
     * Converts a set of result messages into a set of output DTOs.
     *
     * <p>This is a convenience method that applies {@link #toDto(String)} to each message in the input set.
     * It is useful for bulk save operations where each result has a corresponding message.
     *
     * @param messages the set of messages to convert; must not be {@code null}
     * @return a set of output DTOs corresponding to the input messages
     * @throws NullPointerException if {@code messages} is {@code null} or contains {@code null} elements
     */
    default Set<DO> toDtos(Set<String> messages) {
        return messages.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Converts the input DTO to a domain entity.
     *
     * <p>This method is used to transform client-facing input into an internal model
     * suitable for persistence or business logic.
     *
     * @param model the input DTO to convert; must not be {@code null}
     * @return the corresponding entity
     * @throws NullPointerException if {@code model} is {@code null}
     */
    E toEntity(DI model);

    /**
     * Converts a set of input DTOs to a set of entities.
     *
     * <p>This is a convenience method that applies {@link #toEntity(Object)} to each input DTO.
     * It is useful for batch operations.
     *
     * @param dtos the set of input DTOs; must not be {@code null}
     * @return a set of entities corresponding to the input DTOs
     * @throws NullPointerException if {@code dtos} is {@code null} or contains {@code null} elements
     */
    default Set<E> toEntity(Set<DI> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
