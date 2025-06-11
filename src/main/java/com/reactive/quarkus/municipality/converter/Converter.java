package com.reactive.quarkus.municipality.converter;


import java.util.Set;
import java.util.stream.Collectors;

/**
 * Generic interface for converting between domain entities and data transfer objects (DTOs).
 *
 * <p>This interface defines bidirectional mapping between:
 * <ul>
 *   <li>{@code E} - the entity (typically a JPA entity or business model),</li>
 *   <li>{@code DI} - the DTO used as input for creating or updating the entity,</li>
 *   <li>{@code DO} - the DTO used for output/display purposes.</li>
 * </ul>
 *
 * <p>The design separates input and output DTOs to allow for different representations of data,
 * such as when exposing less information for input than for output.
 *
 * @param <DI> the type of input DTO (used for converting to entities)
 * @param <DO> the type of output DTO (used for converting from entities)
 * @param <E>  the type of entity (domain model)
 */
public interface Converter<DI, DO, E> {

    /**
     * Converts the given entity to an output DTO.
     *
     * @param entity the entity to convert; must not be {@code null}
     * @return a DTO representation of the entity
     * @throws NullPointerException if {@code entity} is {@code null}
     */
    DO toDto(E entity);

    /**
     * Converts a set of entities to a set of output DTOs.
     *
     * <p>This is a convenience method that applies {@link #toDto(Object)} to each element.
     * The resulting set retains the uniqueness properties of the input set based on {@code DO#equals()}.
     *
     * @param entities the set of entities to convert; must not be {@code null}
     * @return a set of DTOs corresponding to the given entities
     * @throws NullPointerException if {@code entities} is {@code null} or contains {@code null} elements
     */
    default Set<DO> toDtos(Set<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Converts the given input DTO to an entity.
     *
     * @param model the input DTO to convert; must not be {@code null}
     * @return the corresponding entity
     * @throws NullPointerException if {@code model} is {@code null}
     */
    E toEntity(DI model);

    /**
     * Converts a set of input DTOs to a set of entities.
     *
     * <p>This is a convenience method that applies {@link #toEntity(Object)} to each element.
     * The resulting set retains the uniqueness properties of the input set based on {@code E#equals()}.
     *
     * @param dtos the set of input DTOs to convert; must not be {@code null}
     * @return a set of entities corresponding to the given DTOs
     * @throws NullPointerException if {@code dtos} is {@code null} or contains {@code null} elements
     */
    default Set<E> toEntity(Set<DI> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
