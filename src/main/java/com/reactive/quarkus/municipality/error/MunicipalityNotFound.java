package com.reactive.quarkus.municipality.error;

import java.time.LocalDateTime;

public record MunicipalityNotFound(String message, LocalDateTime timeStamp, String classHappen) implements MunicipalityError {

    public MunicipalityNotFound(String classHappen) {
        this("Address not found", LocalDateTime.now(), classHappen);
    }
}
