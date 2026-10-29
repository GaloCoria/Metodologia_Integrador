package org.example.model;

public class Solicitud {

    private final int id;
    private final Estudiante estudiante;
    private final TipoSolicitud tipo;
    private final Prioridad prioridad;
    private final String descripcion;
    private EstadoSolicitud estado;
    private Responsable responsable;

    public Solicitud(int id, Estudiante estudiante, TipoSolicitud tipo, Prioridad prioridad, String descripcion) {
        this.id = id;
        this.estudiante = estudiante;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.descripcion = descripcion;
        this.estado = EstadoSolicitud.ABIERTA;
    }

    public void asignarA(Responsable responsable) {
        this.responsable = responsable;
        this.estado = EstadoSolicitud.ASIGNADA;
    }

    public void cambiarEstado(EstadoSolicitud nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public TipoSolicitud getTipo() {
        return tipo;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public Responsable getResponsable() {
        return responsable;
    }
}
