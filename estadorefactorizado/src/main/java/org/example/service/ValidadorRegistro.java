package org.example.service;

import org.example.model.RegistroSolicitudDTO;

import java.util.Optional;

public class ValidadorRegistro {

    private static final int LONGITUD_MINIMA_DESCRIPCION = 5;

    /** Devuelve el mensaje de error de la primera regla incumplida, o vacio si los datos son validos. */
    public Optional<String> validar(RegistroSolicitudDTO datos) {
        if (estaVacio(datos.getLegajoEstudiante())) {
            return Optional.of("ERROR: Legajo vacio");
        }
        if (estaVacio(datos.getNombreEstudiante())) {
            return Optional.of("ERROR: Nombre vacio");
        }
        if (datos.getEmailEstudiante() == null || !datos.getEmailEstudiante().contains("@")) {
            return Optional.of("ERROR: Mail invalido");
        }
        if (datos.getDescripcion() == null || datos.getDescripcion().length() < LONGITUD_MINIMA_DESCRIPCION) {
            return Optional.of("ERROR: Descripcion muy corta");
        }
        return Optional.empty();
    }

    private boolean estaVacio(String texto) {
        return texto == null || texto.isEmpty();
    }
}
