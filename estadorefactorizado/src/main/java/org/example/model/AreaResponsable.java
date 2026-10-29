package org.example.model;

public enum AreaResponsable {
    ALUMNOS("Alumnos"),
    ACADEMICA("Academica");

    private final String nombre;

    AreaResponsable(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
