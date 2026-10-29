package org.example.repository;

import org.example.model.EstadoSolicitud;
import org.example.model.Solicitud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Repositorio en memoria: unica responsabilidad, guardar y consultar solicitudes. */
public class SolicitudRepository {

    private final List<Solicitud> solicitudes = new ArrayList<>();
    private int proximoId = 1;

    public int generarId() {
        return proximoId++;
    }

    public void guardar(Solicitud solicitud) {
        solicitudes.add(solicitud);
    }

    public Optional<Solicitud> buscarPorId(int id) {
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getId() == id) {
                return Optional.of(solicitud);
            }
        }
        return Optional.empty();
    }

    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {
        List<Solicitud> resultado = new ArrayList<>();
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getEstado() == estado) {
                resultado.add(solicitud);
            }
        }
        return resultado;
    }

    public List<Solicitud> listarTodas() {
        return new ArrayList<>(solicitudes);
    }
}
