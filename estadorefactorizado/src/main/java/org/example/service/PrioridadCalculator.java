package org.example.service;

import org.example.model.Prioridad;
import org.example.model.TipoSolicitud;

public class PrioridadCalculator {

    public Prioridad calcular(TipoSolicitud tipo, boolean urgente) {
        if (urgente) {
            return Prioridad.ALTA;
        }
        return tipo.getPrioridadPorDefecto();
    }
}
