package org.example.model;

import java.util.Optional;

public enum EstadoSolicitud {
    ABIERTA,
    ASIGNADA,
    CERRADA,
    RECHAZADA;

    public boolean estaFinalizada() {
        return this == CERRADA || this == RECHAZADA;
    }

    public static Optional<EstadoSolicitud> desdeNombre(String nombre) {
        for (EstadoSolicitud estado : values()) {
            if (estado.name().equals(nombre)) {
                return Optional.of(estado);
            }
        }
        return Optional.empty();
    }
}
