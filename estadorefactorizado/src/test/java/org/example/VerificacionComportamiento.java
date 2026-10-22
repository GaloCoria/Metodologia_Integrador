package org.example;

import org.example.model.AreaResponsable;
import org.example.model.EstadoSolicitud;
import org.example.model.Prioridad;
import org.example.model.RegistroSolicitudDTO;
import org.example.model.Responsable;
import org.example.repository.SolicitudRepository;

/** Verificacion sin dependencias externas. Ejecutar: java -cp <classes> org.example.VerificacionComportamiento */
public class VerificacionComportamiento {

    private static int fallos = 0;

    public static void main(String[] args) {
        SistemaSolicitudes sys = new SistemaSolicitudes(new SolicitudRepository(), mensaje -> { });
        Responsable alumnos = new Responsable("R1", "Carlos", AreaResponsable.ALUMNOS, true);
        Responsable academica = new Responsable("R2", "Ana", AreaResponsable.ACADEMICA, true);
        Responsable inactivo = new Responsable("R3", "Inactivo", AreaResponsable.ACADEMICA, false);

        esperar("Legajo vacio", "ERROR: Legajo vacio", sys.registrar(dto("", "Juan", "j@x.com", "1", "descripcion", false)));
        esperar("Nombre vacio", "ERROR: Nombre vacio", sys.registrar(dto("1", "", "j@x.com", "1", "descripcion", false)));
        esperar("Mail invalido", "ERROR: Mail invalido", sys.registrar(dto("1", "Juan", "jx.com", "1", "descripcion", false)));
        esperar("Descripcion corta", "ERROR: Descripcion muy corta", sys.registrar(dto("1", "Juan", "j@x.com", "1", "abc", false)));
        esperar("Registro OK id 1", "OK:1", sys.registrar(dto("1", "Juan", "j@x.com", "1", "descripcion", false)));
        esperar("Registro OK id 2", "OK:2", sys.registrar(dto("2", "Maria", "m@x.com", "2", "descripcion", false)));
        esperar("Registro OK id 3", "OK:3", sys.registrar(dto("3", "Lucas", "l@x.com", "3", "descripcion", false)));
        esperar("Registro urgente id 4", "OK:4", sys.registrar(dto("4", "Ana", "a@x.com", "1", "descripcion", true)));

        esperar("Prioridad tipo 1 = BAJA", Prioridad.BAJA, sys.buscarPorEstado(EstadoSolicitud.ABIERTA).get(0).getPrioridad());
        esperar("Prioridad tipo 2 = MEDIA", Prioridad.MEDIA, sys.buscarPorEstado(EstadoSolicitud.ABIERTA).get(1).getPrioridad());
        esperar("Prioridad tipo 3 = ALTA", Prioridad.ALTA, sys.buscarPorEstado(EstadoSolicitud.ABIERTA).get(2).getPrioridad());
        esperar("Prioridad urgente = ALTA", Prioridad.ALTA, sys.buscarPorEstado(EstadoSolicitud.ABIERTA).get(3).getPrioridad());

        esperar("Asignar inexistente", "ERROR: No existe", sys.asignarResponsable(99, alumnos));
        esperar("Area incorrecta", "ERROR: Responsable incompatible o inactivo", sys.asignarResponsable(2, alumnos));
        esperar("Responsable inactivo", "ERROR: Responsable incompatible o inactivo", sys.asignarResponsable(2, inactivo));
        esperar("Responsable null", "ERROR: Responsable incompatible o inactivo", sys.asignarResponsable(2, null));
        esperar("Asignar OK", "OK", sys.asignarResponsable(2, academica));
        esperar("Estado tras asignar", 1, sys.buscarPorEstado(EstadoSolicitud.ASIGNADA).size());

        esperar("Cambiar a CERRADA", "OK", sys.cambiarEstado(1, "CERRADA"));
        esperar("Estado invalido", "ERROR: Estado invalido", sys.cambiarEstado(1, "XXX"));
        esperar("Cambiar inexistente", "ERROR: No existe", sys.cambiarEstado(99, "ABIERTA"));
        esperar("No asignar finalizada", "ERROR: No se puede asignar una solicitud finalizada", sys.asignarResponsable(1, alumnos));

        esperar("Reporte", "=== REPORTE DE SOLICITUDES ===\nTotal: 4\nAbiertas: 2 | Asignadas: 1 | Cerradas: 1 | Rechazadas: 0\nDe prioridad ALTA: 2\n", sys.generarReporte());

        System.out.println(fallos == 0 ? "TODAS LAS VERIFICACIONES PASARON" : "FALLOS: " + fallos);
        if (fallos > 0) {
            System.exit(1);
        }
    }

    private static RegistroSolicitudDTO dto(String legajo, String nombre, String mail, String tipo, String desc, boolean urgente) {
        return new RegistroSolicitudDTO(legajo, nombre, mail, tipo, desc, urgente);
    }

    private static void esperar(String caso, Object esperado, Object obtenido) {
        boolean ok = esperado.equals(obtenido);
        if (!ok) {
            fallos++;
        }
        System.out.println((ok ? "[OK]   " : "[FAIL] ") + caso + (ok ? "" : " -> esperado: " + esperado + " | obtenido: " + obtenido));
    }
}
