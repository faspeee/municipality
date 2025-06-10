package com.reactive.quarkus.municipality.error;

import java.time.LocalDateTime;

public record MunicipalityServerError(String message, LocalDateTime timeStamp, String classHappen) implements MunicipalityError {
    public MunicipalityServerError(String message, String classHappen) {
        this(message, LocalDateTime.now(), classHappen);
    }

}
