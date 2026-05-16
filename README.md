# Evaluación Final — Automatización de Pruebas

**Autor:** Victor Chávez Gaete  
**Repositorio:** https://github.com/vchavezgaete/Victor_Chavez_Gaete_Evaluacion_Final

Proyecto Maven (Java 17) con gestión de pedidos, pruebas automatizadas en tres niveles y pipelines de integración continua y despliegue.

## Descripción

El sistema modela pedidos con líneas de detalle, valida reglas comerciales (`ValidacionPedidoService`), calcula subtotal, IVA y total (`CalculadoraPedidoService`) y expone el flujo completo en `PedidoService`. La clase `App` ejecuta un ejemplo por consola.

## Versionado (GitFlow)

| Rama | Uso |
|------|-----|
| `main` | Versión estable |
| `develop` | Integración de cambios |
| `feature/configuracion-maven` | Configuración Maven y dependencias |
| `feature/pruebas-unitarias` | Pruebas Surefire |
| `feature/pruebas-integracion` | Pruebas Failsafe de integración |
| `release/examen-final` | Entrega del examen |

## Stack

- Java 17, Maven, JUnit 5, Selenium (dependencia de pruebas)
- Jenkins (pipeline declarativo)
- Git / GitHub

## Pruebas

| Tipo | Paquete | Comando |
|------|---------|---------|
| Unitarias | `unit` | `mvn test` |
| Integración | `integration` (`*IT.java`) | `mvn verify` |
| Aceptación | `acceptance` (`*AcceptanceTest.java`) | `mvn verify` |

Surefire ejecuta solo las unitarias; Failsafe ejecuta integración y aceptación.

## Ejecución local

```bash
mvn clean compile
mvn test
mvn verify
mvn -q compile exec:java
```

## CI (`Jenkinsfile`)

Etapas: Checkout → Build (`mvn compile`) → Unit Tests → Integration Tests (`mvn verify`) → publicación de artefactos y reportes JUnit.

Configurar un job Pipeline apuntando a este repositorio, rama `main`, script `Jenkinsfile`. El agente debe tener Maven y JDK; los pasos usan shell Linux (`sh`).

## Despliegue

Ver `deployment-pipeline.md` para el flujo build → pruebas → despliegue en ambiente de prueba → rollback. El script `rollback.sh` simula la reversión a la versión anterior.

## Evidencias

Las capturas del informe se almacenan en `evidencias/actividad-1`, `actividad-2` y `actividad-3`.
