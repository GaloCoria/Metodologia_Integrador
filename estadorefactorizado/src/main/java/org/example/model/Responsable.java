package org.example.model;

public class Responsable {

    private final String id;
    private final String nombre;
    private final AreaResponsable area;
    private final boolean activo;

    public Responsable(String id, String nombre, AreaResponsable area, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public AreaResponsable getArea() {
        return area;
    }

    public boolean isActivo() {
        return activo;
    }
}
