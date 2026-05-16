#!/usr/bin/env bash
# Rollback en ambiente de prueba (simulado)
set -euo pipefail

echo "[1/5] Detener version desplegada"
echo "[2/5] Restaurar artefacto anterior (pedido-api-previous.jar)"
echo "[3/5] Reiniciar servicio en staging"
echo "[4/5] Verificar healthcheck"
echo "[5/5] Rollback completado - version anterior activa"
