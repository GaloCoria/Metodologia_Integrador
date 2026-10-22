package org.example.model;

/** Parameter Object: agrupa los datos de entrada de registrar(). El tipo viaja con su codigo externo ("1", "2", "3"). */
public class RegistroSolicitudDTO {

    private final String legajoEstudiante;
    private final String nombreEstudiante;
    private final String emailEstudiante;
    private final String codigoTipo;
    private final String descripcion;
    private final boolean urgente;

    public RegistroSolicitudDTO(String legajoEstudiante, String nombreEstudiante, String emailEstudiante,
                                String codigoTipo, String descripcion, boolean urgente) {
        this.legajoEstudiante = legajoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.emailEstudiante = emailEstudiante;
        this.codigoTipo = codigoTipo;
        this.descripcion = descripcion;
        this.urgente = urgente;
    }

    public String getLegajoEstudiante() {
        return legajoEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public String getEmailEstudiante() {
        return emailEstudiante;
    }

    public String getCodigoTipo() {
        return codigoTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isUrgente() {
        return urgente;
    }
}
