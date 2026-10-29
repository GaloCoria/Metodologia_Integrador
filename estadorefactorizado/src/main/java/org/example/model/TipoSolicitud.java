package org.example.model;

import java.util.Optional;

/** Cada tipo conoce su codigo externo, su prioridad por defecto y el area que lo atiende (OCP: un tipo nuevo = una constante nueva). */
public enum TipoSolicitud {
    CERTIFICADO("1", Prioridad.BAJA, AreaResponsable.ALUMNOS),
    EQUIVALENCIA("2", Prioridad.MEDIA, AreaResponsable.ACADEMICA),
    RECLAMO_NOTA("3", Prioridad.ALTA, AreaResponsable.ACADEMICA);

    private final String codigo;
    private final Prioridad prioridadPorDefecto;
    private final AreaResponsable areaResponsable;

    TipoSolicitud(String codigo, Prioridad prioridadPorDefecto, AreaResponsable areaResponsable) {
        this.codigo = codigo;
        this.prioridadPorDefecto = prioridadPorDefecto;
        this.areaResponsable = areaResponsable;
    }

    public String getCodigo() {
        return codigo;
    }

    public Prioridad getPrioridadPorDefecto() {
        return prioridadPorDefecto;
    }

    public AreaResponsable getAreaResponsable() {
        return areaResponsable;
    }

    public static Optional<TipoSolicitud> desdeCodigo(String codigo) {
        for (TipoSolicitud tipo : values()) {
            if (tipo.codigo.equals(codigo)) {
                return Optional.of(tipo);
            }
        }
        return Optional.empty();
    }
}
