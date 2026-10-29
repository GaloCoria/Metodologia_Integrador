# Metodologia_Integrador — Sistema de Solicitudes Académicas

Trabajo Práctico Integrador de **Metodología de Sistemas II** (TUP a Distancia, UTN):
evolución de un sistema existente mediante diagnóstico de deuda técnica, refactoring seguro y code review.

## Descripción

Módulo de gestión de solicitudes académicas: un estudiante registra una solicitud (certificado, equivalencia, reclamo de nota),
que se clasifica por tipo, prioridad y estado, se asigna a un responsable y se consulta en un reporte.
El repositorio contiene una **versión inicial con problemas reales de diseño** y una **versión refactorizada** con el mismo comportamiento externo.

## Estructura

| Ruta | Contenido |
|------|-----------|
| `01-version-inicial/` | Versión base con deuda técnica (tag `estado-inicial`). No se modifica. |
| `estadorefactorizado/` | Versión refactorizada (tag `estadorefactorizado`) y su verificación en `src/test/java`. |
| `docs/diagnostico-code-smells.md` | Diagnóstico de los 8 code smells. |
| `docs/checklist-code-review.md` | Checklist técnico y observaciones del review. |
| `docs/decisiones-tecnicas.md` | Decisiones de diseño y su justificación. |
| `docs/evidencias/` | Salidas antes/después y resultado de las verificaciones. |

## Funcionalidades

- Registrar solicitud (con validación de legajo, nombre, correo y descripción).
- Asignar responsable (según área, estado activo y solicitud no finalizada).
- Cambiar estado (ABIERTA, ASIGNADA, CERRADA, RECHAZADA).
- Buscar solicitudes por estado.
- Generar reporte (total, por estado y de prioridad ALTA).

## Tecnologías

Java 17, Maven (opcional), sin dependencias externas. Persistencia simulada en memoria.

## Instalación y uso

Requiere JDK 17 o superior.

```bash
# Versión refactorizada
cd estadorefactorizado
javac -d out $(find src -name '*.java')
java -cp out org.example.SistemaSolicitudes
java -cp out org.example.VerificacionComportamiento

# Versión inicial
cd ../01-version-inicial
javac -d out src/main/java/org/example/*.java
java -cp out org.example.SistemaSolicitudes
```

## Tags

`estado-inicial`, `estadorefactorizado`, `etapa-diagnostico`, `etapa-refactoring`, `etapa-pruebas`, `etapa-docs`.

## Integrantes

- Galo Coria Maiorano
- Gragera Lucas
- Zullo Lautaro

Video de defensa: _(completar enlace)_
