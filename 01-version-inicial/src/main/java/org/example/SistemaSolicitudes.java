package org.example;

import java.util.ArrayList;
import java.util.List;

public class SistemaSolicitudes {


    public static List<Solicitud> dbSolicitudes = new ArrayList<>();
    public static int contadorId = 1;


    public String registrar(String legajoEst, String nomEst, String mailEst, String t, String desc, boolean esUrgente) {
        if (legajoEst == null || legajoEst.isEmpty()) {
            return "ERROR: Legajo vacio";
        }
        if (nomEst == null || nomEst.isEmpty()) {
            return "ERROR: Nombre vacio";
        }
        if (mailEst == null || !mailEst.contains("@")) {
            return "ERROR: Mail invalido";
        }
        if (desc == null || desc.length() < 5) {
            return "ERROR: Descripcion muy corta";
        }

        Estudiante est = new Estudiante(legajoEst, nomEst, mailEst);
        Solicitud s = new Solicitud();
        s.id = contadorId++;
        s.est = est;
        s.t = t;
        s.desc = desc;
        s.e = "ABIERTA"; // Magic String


        if (esUrgente == true) {
            s.p = 1;
        } else {
            if (t.equals("3")) {
                s.p = 1;
            } else if (t.equals("2")) {
                s.p = 2;
            } else {
                s.p = 3;
            }
        }

        dbSolicitudes.add(s);
        System.out.println("LOG: Se guardo la solicitud " + s.id + " del alumno " + nomEst);
        return "OK:" + s.id;
    }


    public String asignarResponsable(int idSol, Responsable resp) {
        Solicitud encontrada = null;
        for (int i = 0; i < dbSolicitudes.size(); i++) {
            if (dbSolicitudes.get(i).id == idSol) {
                encontrada = dbSolicitudes.get(i);
                break;
            }
        }
        if (encontrada == null) return "ERROR: No existe";

        if (encontrada.e.equals("CERRADA") || encontrada.e.equals("RECHAZADA")) {
            return "ERROR: No se puede asignar una solicitud finalizada";
        }

        if (encontrada.puedeAsignarseA(resp)) {
            encontrada.resp = resp;
            encontrada.e = "ASIGNADA";
            System.out.println("LOG: Solicitud " + idSol + " asignada a " + resp.nombre);
            return "OK";
        } else {
            return "ERROR: Responsable incompatible o inactivo";
        }
    }


    public String cambiarEstado(int idSol, String nuevoEstado) {
        Solicitud encontrada = null;
        for (int i = 0; i < dbSolicitudes.size(); i++) {
            if (dbSolicitudes.get(i).id == idSol) {
                encontrada = dbSolicitudes.get(i);
                break;
            }
        }
        if (encontrada == null) return "ERROR: No existe";

        if (nuevoEstado.equals("ABIERTA") || nuevoEstado.equals("ASIGNADA") ||
                nuevoEstado.equals("CERRADA") || nuevoEstado.equals("RECHAZADA")) {
            encontrada.e = nuevoEstado;
            return "OK";
        }
        return "ERROR: Estado invalido";
    }


    public List<Solicitud> buscarPorEstado(String est) {
        List<Solicitud> res = new ArrayList<>();
        for (Solicitud s : dbSolicitudes) {
            if (s.e.equals(est)) {
                res.add(s);
            }
        }
        return res;
    }


    public String generarReporte() {
        int a = 0, asig = 0, c = 0, r = 0;
        int pAlta = 0;
        for (Solicitud s : dbSolicitudes) {
            if (s.e.equals("ABIERTA")) a++;
            if (s.e.equals("ASIGNADA")) asig++;
            if (s.e.equals("CERRADA")) c++;
            if (s.e.equals("RECHAZADA")) r++;
            if (s.p == 1) pAlta++;
        }
        String rep = "=== REPORTE DE SOLICITUDES ===\n";
        rep += "Total: " + dbSolicitudes.size() + "\n";
        rep += "Abiertas: " + a + " | Asignadas: " + asig + " | Cerradas: " + c + " | Rechazadas: " + r + "\n";
        rep += "De prioridad ALTA: " + pAlta + "\n";
        System.out.println(rep);
        return rep;
    }

    
    public static void main(String[] args) {
        SistemaSolicitudes sys = new SistemaSolicitudes();
        Responsable r1 = new Responsable("R1", "Carlos Gomez", "Alumnos", true);
        Responsable r2 = new Responsable("R2", "Ana Lopez", "Academica", true);

        System.out.println("--- 1. Registrando Solicitudes ---");
        sys.registrar("1001", "Juan Perez", "juan@utn.edu.ar", "1", "Pedido de certificado de alumno regular", false);
        sys.registrar("1002", "Maria Garcia", "maria@utn.edu.ar", "2", "Solicitud de equivalencia Matematica I", false);
        sys.registrar("1003", "Lucas Diaz", "lucas@utn.edu.ar", "3", "Reclamo de nota parcial Programacion", true);

        System.out.println("\n--- 2. Asignando Responsables ---");
        System.out.println("Asignar Sol 1 a R1: " + sys.asignarResponsable(1, r1));
        System.out.println("Asignar Sol 2 a R1 (Area incorrecta): " + sys.asignarResponsable(2, r1));
        System.out.println("Asignar Sol 2 a R2 (Area correcta): " + sys.asignarResponsable(2, r2));

        System.out.println("\n--- 3. Cambiando Estados ---");
        sys.cambiarEstado(1, "CERRADA");

        System.out.println("\n--- 4. Buscando Solicitudes ABIERTAS ---");
        List<Solicitud> abiertas = sys.buscarPorEstado("ABIERTA");
        System.out.println("Cantidad de abiertas: " + abiertas.size());

        System.out.println("\n--- 5. Reporte General ---");
        sys.generarReporte();
    }
}