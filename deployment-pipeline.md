# Pipeline de despliegue

Flujo de entrega del artefacto Maven (`target/*.jar`) hacia un ambiente de prueba, con validación previa y rollback.

## Etapas

### 1. Build

Compilación y empaquetado con JDK 17.

```bash
mvn clean package
```

### 2. Pruebas unitarias

Validación de componentes aislados (Surefire, paquete `unit`).

```bash
mvn test
```

### 3. Pruebas de integración

Validación de interacción entre servicios (`*IT.java`, Failsafe).

### 4. Pruebas de aceptación

Escenarios de negocio (`*AcceptanceTest.java`, Failsafe).

```bash
mvn verify
```

### 5. Deploy (ambiente de prueba)

Despliegue del JAR en staging/QA: copia del artefacto, reinicio del servicio y smoke tests.

### 6. Rollback

Ante fallas post-despliegue, retorno a la versión estable. Ver `rollback.sh`.

## Relación con CI

El `Jenkinsfile` cubre build y pruebas en el agente de CI. Las etapas 5 y 6 se ejecutan en el entorno de destino con credenciales e infraestructura propias del ambiente.
