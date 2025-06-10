package com.reactive.quarkus.municipality.error;

public sealed interface Success permits MunicipalitySuccess, MunicipalityIsPresent {
}
