# Pipeline de despliegue (CD)

Documento del flujo de entrega hacia el ambiente de prueba (staging/QA), alineado con las pruebas automatizadas del proyecto.

## Diagrama de etapas

```
Build → Unit Tests → Integration Tests → Acceptance Tests → Deploy (test) → [Rollback si falla]
```

## 1. Build

Genera el JAR ejecutable.

```bash
mvn clean package -DskipTests
```

## 2. Pruebas unitarias

Componentes aislados (`src/test/java/.../unit/`, Maven Surefire).

```bash
mvn test
```

## 3. Pruebas de integración

Interacción entre servicios (`*IT.java`, Maven Failsafe).

## 4. Pruebas de aceptación

Criterios de negocio (`*AcceptanceTest.java`, Maven Failsafe).

```bash
mvn verify
```

## 5. Despliegue en ambiente de prueba

Instalación del artefacto en staging. Script: `deploy.sh`.

Actividades: copia del JAR, reinicio del servicio, smoke test HTTP.

## 6. Rollback

Ante errores post-despliegue se revierte a la versión estable anterior. Script: `rollback.sh`.

**Estrategia elegida:** rollback automático (alternativa válida: Blue-Green o Canary en infraestructura con balanceador).

## Automatización Jenkins

| Archivo | Uso |
|---------|-----|
| `Jenkinsfile` | CI: build + pruebas |
| `Jenkinsfile.deploy` | CD: build + pruebas + despliegue + rollback en fallo |

## Ejecución manual

```bash
chmod +x deploy.sh rollback.sh
./deploy.sh
./rollback.sh
```
