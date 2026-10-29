# Diagnostico de code smells - 01-version-inicial

| # | Smell | Ubicacion | Refactoring aplicado |
|---|-------|-----------|----------------------|
| 1 | Long Parameter List / Data Clumps | `SistemaSolicitudes.registrar(...)` (6 parametros) | Introduce Parameter Object (`RegistroSolicitudDTO`) |
| 2 | Long Method | `SistemaSolicitudes.registrar(...)` valida, instancia, calcula prioridad, guarda y loguea | Extract Method / Extract Class |
| 3 | Duplicated Code | bucle de busqueda por id en `asignarResponsable` y `cambiarEstado` | Extract Method (`SolicitudRepository.buscarPorId`) |
| 4 | Primitive Obsession / Magic Strings | estados `"ABIERTA"`..., tipos `"1"`,`"2"`,`"3"`, prioridades 1,2,3 | Enums |
| 5 | God Class / SRP | `SistemaSolicitudes` (repositorio, negocio, reporte, logger) | Extract Class |
| 6 | Feature Envy | `Solicitud.puedeAsignarseA(Responsable)` | Move Method (`AsignacionService`) |
| 7 | Campos publicos | `Solicitud`, `Estudiante`, `Responsable`, `dbSolicitudes` | Encapsulate Field |
| 8 | Nombres poco descriptivos | `t`, `p`, `e`, `est`, `resp`; `a`, `c`, `r` en `generarReporte` | Rename |
