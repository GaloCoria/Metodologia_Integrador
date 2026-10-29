package org.example.service;

import org.example.model.EstadoSolicitud;
import org.example.model.Prioridad;
import org.example.model.Solicitud;

import java.util.List;

public class ReporteService {

    public String generar(List<Solicitud> solicitudes) {
        int abiertas = contarPorEstado(solicitudes, EstadoSolicitud.ABIERTA);
        int asignadas = contarPorEstado(solicitudes, EstadoSolicitud.ASIGNADA);
        int cerradas = contarPorEstado(solicitudes, EstadoSolicitud.CERRADA);
        int rechazadas = contarPorEstado(solicitudes, EstadoSolicitud.RECHAZADA);
        int prioridadAlta = contarPorPrioridad(solicitudes, Prioridad.ALTA);

        StringBuilder reporte = new StringBuilder("=== REPORTE DE SOLICITUDES ===\n");
        reporte.append("Total: ").append(solicitudes.size()).append("\n");
        reporte.append("Abiertas: ").append(abiertas)
                .append(" | Asignadas: ").append(asignadas)
                .append(" | Cerradas: ").append(cerradas)
                .append(" | Rechazadas: ").append(rechazadas).append("\n");
        reporte.append("De prioridad ALTA: ").append(prioridadAlta).append("\n");
        return reporte.toString();
    }

    private int contarPorEstado(List<Solicitud> solicitudes, EstadoSolicitud estado) {
        int cantidad = 0;
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getEstado() == estado) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private int contarPorPrioridad(List<Solicitud> solicitudes, Prioridad prioridad) {
        int cantidad = 0;
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getPrioridad() == prioridad) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
