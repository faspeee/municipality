package com.reactive.quarkus.municipality.converter;


import java.util.Set;
import java.util.stream.Collectors;

public interface Converter<DI, DO, E> {
    DO toDto(E entity);

    default Set<DO> toDtos(Set<E> entity) {
        return entity.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    default Set<E> toEntity(Set<DI> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    E toEntity(DI model);
}
