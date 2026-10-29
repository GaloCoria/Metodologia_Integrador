# Estado refactorizado - Sistema de Solicitudes Academicas

Version refactorizada de `01-version-inicial` (mismo comportamiento externo).

Estructura: `model/` (enums y entidades encapsuladas, `RegistroSolicitudDTO`), `repository/` (`SolicitudRepository`),
`service/` (validacion, prioridad, asignacion, reporte), `util/` (`Logger`), `SistemaSolicitudes` (fachada).

Ejecutar (Java 17+):

    javac -d out $(find src -name '*.java')
    java -cp out org.example.SistemaSolicitudes
    java -cp out org.example.VerificacionComportamiento

`../docs/evidencias/` contiene la salida de ambas versiones (identicas) y el resultado de las verificaciones.
