# Decisiones técnicas

| # | Decisión | Motivo | Alternativa descartada |
|---|----------|--------|------------------------|
| D1 | Refactorizar en `estadorefactorizado/` y dejar `01-version-inicial/` intacta | Permite comparar antes/después y reproducir la línea base | Modificar la versión inicial en su lugar |
| D2 | Fijar como línea base la salida de `main()` y los mensajes literales de error | La versión inicial no tenía pruebas | Escribir pruebas nuevas sobre la versión inicial |
| D3 | Enums con datos (`TipoSolicitud` lleva código, prioridad y área) | Cumple OCP: un tipo nuevo es una constante nueva | Jerarquía de clases / patrón Strategy (sobreingeniería para este tamaño) |
| D4 | Conservar la fachada `SistemaSolicitudes` y sus mensajes (`OK`, `OK:<id>`, `ERROR: ...`) | Mantiene el comportamiento externo | Lanzar excepciones o devolver objetos resultado |
| D5 | `RegistroSolicitudDTO` inmutable como Parameter Object | Elimina la lista larga de parámetros | Builder (se difiere hasta que crezcan los campos) |
| D6 | `Optional` para búsquedas y validaciones | Evita `null` y `NullPointerException` | Devolver `null` |
| D7 | Repositorio y logger inyectados por constructor | Elimina el estado estático y permite probar con un logger nulo | Singleton estático |
| D8 | Verificación sin framework (`VerificacionComportamiento`) | Compila con `javac` puro, sin descargas | JUnit 5 (queda como mejora futura) |
| D9 | Tipo desconocido pasa a devolver `ERROR: Tipo invalido` | Única diferencia de comportamiento, deliberada: antes se aceptaba en silencio o lanzaba `NullPointerException` | Replicar el comportamiento defectuoso |

## Mejoras futuras

JUnit 5 con Maven, `cambiarEstado` con el enum, transiciones de estado válidas, interfaz para el repositorio con implementación persistente y análisis estático.
