package org.example;

import org.example.model.AreaResponsable;
import org.example.model.EstadoSolicitud;
import org.example.model.Estudiante;
import org.example.model.Prioridad;
import org.example.model.RegistroSolicitudDTO;
import org.example.model.Responsable;
import org.example.model.Solicitud;
import org.example.model.TipoSolicitud;
import org.example.repository.SolicitudRepository;
import org.example.service.AsignacionService;
import org.example.service.PrioridadCalculator;
import org.example.service.ReporteService;
import org.example.service.ValidadorRegistro;
import org.example.util.ConsoleLogger;
import org.example.util.Logger;

import java.util.List;
import java.util.Optional;

/** Fachada de casos de uso: coordina colaboradores (DIP: repositorio y logger se inyectan). */
public class SistemaSolicitudes {

    private static final String OK = "OK";
    private static final String ERROR_NO_EXISTE = "ERROR: No existe";

    private final SolicitudRepository repositorio;
    private final Logger logger;
    private final ValidadorRegistro validador = new ValidadorRegistro();
    private final PrioridadCalculator prioridadCalculator = new PrioridadCalculator();
    private final AsignacionService asignacionService = new AsignacionService();
    private final ReporteService reporteService = new ReporteService();

    public SistemaSolicitudes() {
        this(new SolicitudRepository(), new ConsoleLogger());
    }

    public SistemaSolicitudes(SolicitudRepository repositorio, Logger logger) {
        this.repositorio = repositorio;
        this.logger = logger;
    }

    public String registrar(RegistroSolicitudDTO datos) {
        Optional<String> error = validador.validar(datos);
        if (error.isPresent()) {
            return error.get();
        }
        Optional<TipoSolicitud> tipo = TipoSolicitud.desdeCodigo(datos.getCodigoTipo());
        if (tipo.isEmpty()) {
            return "ERROR: Tipo invalido";
        }

        Estudiante estudiante = new Estudiante(datos.getLegajoEstudiante(), datos.getNombreEstudiante(), datos.getEmailEstudiante());
        Prioridad prioridad = prioridadCalculator.calcular(tipo.get(), datos.isUrgente());
        Solicitud solicitud = new Solicitud(repositorio.generarId(), estudiante, tipo.get(), prioridad, datos.getDescripcion());

        repositorio.guardar(solicitud);
        logger.log("LOG: Se guardo la solicitud " + solicitud.getId() + " del alumno " + estudiante.getNombre());
        return OK + ":" + solicitud.getId();
    }

    public String asignarResponsable(int idSolicitud, Responsable responsable) {
        Optional<Solicitud> solicitud = repositorio.buscarPorId(idSolicitud);
        if (solicitud.isEmpty()) {
            return ERROR_NO_EXISTE;
        }
        if (solicitud.get().getEstado().estaFinalizada()) {
            return "ERROR: No se puede asignar una solicitud finalizada";
        }
        if (!asignacionService.puedeAsignarse(solicitud.get(), responsable)) {
            return "ERROR: Responsable incompatible o inactivo";
        }
        solicitud.get().asignarA(responsable);
        logger.log("LOG: Solicitud " + idSolicitud + " asignada a " + responsable.getNombre());
        return OK;
    }

    public String cambiarEstado(int idSolicitud, String nombreNuevoEstado) {
        Optional<Solicitud> solicitud = repositorio.buscarPorId(idSolicitud);
        if (solicitud.isEmpty()) {
            return ERROR_NO_EXISTE;
        }
        Optional<EstadoSolicitud> nuevoEstado = EstadoSolicitud.desdeNombre(nombreNuevoEstado);
        if (nuevoEstado.isEmpty()) {
            return "ERROR: Estado invalido";
        }
        solicitud.get().cambiarEstado(nuevoEstado.get());
        return OK;
    }

    public List<Solicitud> buscarPorEstado(EstadoSolicitud estado) {
        return repositorio.buscarPorEstado(estado);
    }

    public String generarReporte() {
        String reporte = reporteService.generar(repositorio.listarTodas());
        logger.log(reporte);
        return reporte;
    }

    public static void main(String[] args) {
        SistemaSolicitudes sys = new SistemaSolicitudes();
        Responsable r1 = new Responsable("R1", "Carlos Gomez", AreaResponsable.ALUMNOS, true);
        Responsable r2 = new Responsable("R2", "Ana Lopez", AreaResponsable.ACADEMICA, true);

        System.out.println("--- 1. Registrando Solicitudes ---");
        sys.registrar(new RegistroSolicitudDTO("1001", "Juan Perez", "juan@utn.edu.ar", "1", "Pedido de certificado de alumno regular", false));
        sys.registrar(new RegistroSolicitudDTO("1002", "Maria Garcia", "maria@utn.edu.ar", "2", "Solicitud de equivalencia Matematica I", false));
        sys.registrar(new RegistroSolicitudDTO("1003", "Lucas Diaz", "lucas@utn.edu.ar", "3", "Reclamo de nota parcial Programacion", true));

        System.out.println("\n--- 2. Asignando Responsables ---");
        System.out.println("Asignar Sol 1 a R1: " + sys.asignarResponsable(1, r1));
        System.out.println("Asignar Sol 2 a R1 (Area incorrecta): " + sys.asignarResponsable(2, r1));
        System.out.println("Asignar Sol 2 a R2 (Area correcta): " + sys.asignarResponsable(2, r2));

        System.out.println("\n--- 3. Cambiando Estados ---");
        sys.cambiarEstado(1, "CERRADA");

        System.out.println("\n--- 4. Buscando Solicitudes ABIERTAS ---");
        List<Solicitud> abiertas = sys.buscarPorEstado(EstadoSolicitud.ABIERTA);
        System.out.println("Cantidad de abiertas: " + abiertas.size());

        System.out.println("\n--- 5. Reporte General ---");
        sys.generarReporte();
    }
}
