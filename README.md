# Evaluación Final — Automatización de Pruebas

**Autor:** Victor Chávez Gaete  
**Repositorio:** https://github.com/vchavezgaete/Victor_Chavez_Gaete_Evaluacion_Final

---

## Descripción del proyecto

Aplicación Java (Maven) que gestiona **pedidos comerciales**: validación de reglas (`ValidacionPedidoService`), cálculo de subtotal, IVA y total (`CalculadoraPedidoService`) y procesamiento del flujo en `PedidoService`. Incluye automatización de pruebas, integración continua (Jenkins) y documentación de despliegue con rollback.

---

## Actividad 1 — Git y Maven

### GitFlow

| Rama | Propósito |
|------|-----------|
| `main` | Código estable |
| `develop` | Integración de desarrollo |
| `feature/configuracion-maven` | Configuración `pom.xml` y plugins |
| `feature/pruebas-unitarias` | Pruebas Surefire |
| `feature/pruebas-integracion` | Pruebas Failsafe IT |
| `release/examen-final` | Versión de entrega |

### Dependencias de pruebas (`pom.xml`)

- **JUnit 5** — pruebas unitarias, integración y aceptación  
- **Selenium** — automatización UI (dependencia declarada para pruebas web futuras)  
- **Maven Surefire** — ejecución de tests unitarios  
- **Maven Failsafe** — ejecución de tests IT y acceptance  

---

## Estrategia de pruebas

| Capa | Paquete | Patrón de clase | Herramienta | Objetivo |
|------|---------|-----------------|-------------|----------|
| Unitarias | `unit` | `*Test.java` | Surefire | Lógica aislada (validación, cálculos) |
| Integración | `integration` | `*IT.java` | Failsafe | Interacción entre servicios |
| Aceptación | `acceptance` | `*AcceptanceTest.java` | Failsafe | Reglas de negocio end-to-end |

**Pirámide:** muchas pruebas rápidas en `unit`, menos en `integration` y escenarios de negocio en `acceptance`.

---

## Cómo ejecutar pruebas y pipelines

### Pruebas locales

```bash
mvn clean compile    # compilación
mvn test             # solo unitarias (Surefire)
mvn verify           # unitarias + integración + aceptación (Failsafe)
mvn -q compile exec:java   # ejemplo consola (App)
```

### Pipeline CI (`Jenkinsfile`)

1. Crear job **Pipeline** en Jenkins.  
2. SCM: Git → `https://github.com/vchavezgaete/Victor_Chavez_Gaete_Evaluacion_Final.git`, rama `main`.  
3. Script Path: `Jenkinsfile`.  
4. Ejecutar **Build Now**.

**Etapas:** Checkout → Build → Unit Tests → Integration Tests (`verify`) → Publish Results.

### Pipeline CD (`Jenkinsfile.deploy`)

Script Path: `Jenkinsfile.deploy`  
**Etapas:** Build → Unit Tests → Integration Tests → Acceptance Tests → Deploy to Test (`deploy.sh`). En fallo se invoca `rollback.sh`.

### Despliegue manual

```bash
chmod +x deploy.sh rollback.sh
./deploy.sh      # build + pruebas + deploy simulado
./rollback.sh    # rollback simulado
```

Detalle en `deployment-pipeline.md`.

---

## Actividad 2 — Evidencias CI

| Archivo | Descripción |
|---------|-------------|
| `Jenkinsfile` | Pipeline versionado en GitHub |
| `evidencias/actividad-2/02-pipeline-exitoso.png` | Captura Jenkins (Stage View SUCCESS) |
| `evidencias/actividad-2/01-jenkinsfile-github.png` | Captura del Jenkinsfile en GitHub |
| `evidencias/actividad-2/mvn-ci.log` | Log de referencia `mvn verify` |

![Jenkinsfile en GitHub](evidencias/actividad-2/01-jenkinsfile-github.png)

![Pipeline Jenkins exitoso](evidencias/actividad-2/02-pipeline-exitoso.png)

---

## Actividad 3 — Evidencias despliegue

| Archivo | Descripción |
|---------|-------------|
| `deployment-pipeline.md` | Documento del pipeline CD |
| `deploy.sh` / `rollback.sh` | Scripts de despliegue y rollback |
| `evidencias/actividad-3/01-deployment-pipeline-md.png` | Captura del documento |
| `evidencias/actividad-3/03-ejecucion-rollback.png` | Captura ejecución rollback |
| `evidencias/actividad-3/deploy.log` | Log de `./deploy.sh` |
| `evidencias/actividad-3/rollback.log` | Log de `./rollback.sh` |

![Pipeline de despliegue](evidencias/actividad-3/01-deployment-pipeline-md.png)

![Ejecución rollback](evidencias/actividad-3/03-ejecucion-rollback.png)

---

## Actividad 1 — Evidencias Git y Maven

![Repositorio GitHub](evidencias/actividad-1/01-repositorio-github.png)

![Ramas GitFlow](evidencias/actividad-1/02-ramas-gitflow.png)

![Configuración pom.xml](evidencias/actividad-1/03-pom-xml.png)

![mvn test](evidencias/actividad-1/04-mvn-test.png)

![mvn verify](evidencias/actividad-1/05-mvn-verify.png)

Logs de respaldo: `evidencias/actividad-1/mvn-test.log`, `mvn-verify.log`.

---

## Estructura del repositorio

```
├── pom.xml
├── Jenkinsfile
├── Jenkinsfile.deploy
├── deployment-pipeline.md
├── deploy.sh
├── rollback.sh
├── src/main/java/...      # código producción
├── src/test/java/.../unit/
├── src/test/java/.../integration/
├── src/test/java/.../acceptance/
└── evidencias/            # capturas y logs
```

---

## Tecnologías

Java 17 · Maven · JUnit 5 · Selenium · Jenkins · Git · GitHub
