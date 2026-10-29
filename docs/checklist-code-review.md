# Checklist de code review

✔ cumple · ✘ no cumple · ◐ parcial

| # | Criterio de aceptación técnica | Inicial | Refactorizada | Evidencia |
|---|--------------------------------|:------:|:-------------:|-----------|
| 1 | Sin campos públicos mutables | ✘ | ✔ | Entidades con `private` |
| 2 | Sin magic strings ni numbers | ✘ | ✔ | Enums y constantes |
| 3 | Nombres que revelan intención | ✘ | ✔ | Rename |
| 4 | Métodos cortos con una sola responsabilidad | ✘ | ✔ | `registrar()` coordina |
| 5 | Sin código duplicado | ✘ | ✔ | `buscarPorId` |
| 6 | Lógica de negocio fuera de las entidades de datos | ✘ | ✔ | `AsignacionService` |
| 7 | Dependencias inyectadas y sin estado static | ✘ | ✔ | Constructor con repositorio y logger |
| 8 | Errores controlados (sin NPE por entradas inválidas) | ✘ | ✔ | `Optional` en enums |
| 9 | Misma salida externa que la versión base | — | ✔ | `salida-inicial.txt` = `salida-refactorizada.txt` |
| 10 | Pruebas ejecutadas y en verde | ✘ | ✔ | `verificacion-pruebas.txt` (23/23) |
| 11 | Una clase = un motivo de cambio (SRP) | ✘ | ✔ | Servicios separados |
| 12 | Extensible sin modificar código existente (OCP) | ✘ | ✔ | `TipoSolicitud` |
| 13 | Dependencias hacia abstracciones (DIP) | ✘ | ◐ | `Logger` sí; repositorio es clase concreta |
| 14 | Transiciones de estado validadas | ✘ | ◐ | `cambiarEstado` acepta cualquier estado válido |
| 15 | Pruebas con framework estándar | ✘ | ◐ | Verificación propia, sin JUnit |

## Observaciones del review

| # | Observación | Severidad | Acción | Estado |
|---|-------------|-----------|--------|--------|
| O1 | Tipos y estados como `String` permitían valores inválidos | Alta | Enums con `Optional` | Corregido |
| O2 | Fachada con muchas responsabilidades y estado estático | Alta | Extraer clases e inyectar dependencias | Corregido |
| O3 | Regla de compatibilidad dentro de la entidad | Media | Mover a `AsignacionService` | Corregido |
| O4 | Tipo desconocido aceptado en silencio | Media | `ERROR: Tipo invalido` | Corregido |
| O5 | `cambiarEstado` recibe `String` existiendo el enum | Baja | Recibir `EstadoSolicitud` | Pendiente |
| O6 | Sin reglas de transición de estado | Media | Modelar transiciones permitidas | Pendiente |
| O7 | Servicios instanciados con `new` en la fachada | Baja | Inyectarlos por constructor | Pendiente |
| O8 | Pruebas sin JUnit | Baja | Migrar a JUnit 5 + Maven | Pendiente |

## Criterios de aceptación

Se acepta la versión refactorizada porque cumple los ítems 1 a 12, mantiene la salida externa idéntica a la base,
pasa las 23 verificaciones y compila con Java 17+ sin dependencias externas.
