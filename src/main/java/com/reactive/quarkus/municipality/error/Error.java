package com.reactive.quarkus.municipality.error;

public sealed interface Error permits MunicipalityError, GenericError {
}
