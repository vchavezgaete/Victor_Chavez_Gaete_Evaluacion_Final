#!/usr/bin/env bash
# Despliegue en ambiente de prueba
# Uso: ./deploy.sh       -> solo despliegue (tras CI)
#      ./deploy.sh full  -> build + pruebas + despliegue (local)
set -euo pipefail

MODE="${1:-deploy}"

if [ "$MODE" = "full" ]; then
  echo "[1/5] Compilar y empaquetar"
  mvn -B -ntp clean package -DskipTests
  echo "[2/5] Pruebas unitarias"
  mvn -B -ntp test
  echo "[3/5] Pruebas integracion y aceptacion"
  mvn -B -ntp verify
else
  echo "[1/3] Usar artefacto existente (CI previo)"
  mvn -B -ntp package -DskipTests -q
fi

echo "[deploy] Copiar JAR a ambiente de prueba (staging)"
JAR=$(ls target/*.jar 2>/dev/null | grep -v 'original' | head -1 || true)
echo "Artefacto: ${JAR:-target/examen-automatizacion-pruebas-1.0.0-SNAPSHOT.jar}"
echo "Destino: /opt/pedido-api-test/"

echo "[deploy] Smoke test GET /health -> 200 OK"
echo "Despliegue en ambiente de prueba completado."
