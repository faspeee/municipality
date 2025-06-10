package com.reactive.quarkus.municipality.error;


public sealed interface MunicipalityError extends Error permits MunicipalityNotFound, MunicipalityServerError {
}
