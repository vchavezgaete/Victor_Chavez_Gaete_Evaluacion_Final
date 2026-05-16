#!/usr/bin/env bash
# Rollback en ambiente de prueba (simulado)
set -euo pipefail

echo "[1/5] Detener despliegue actual"
echo "[2/5] Apagar servicio (systemctl/docker)"
echo "[3/5] Restaurar artefacto anterior"
echo "[4/5] Verificar healthcheck"
echo "[5/5] Rollback completado"
