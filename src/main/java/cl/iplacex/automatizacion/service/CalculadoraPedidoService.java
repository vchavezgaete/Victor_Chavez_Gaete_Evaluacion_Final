package cl.iplacex.automatizacion.service;

import cl.iplacex.automatizacion.model.LineaPedido;

import java.util.List;
import java.util.Objects;

/**
 * Cálculos de montos sobre líneas de pedido.
 */
public class CalculadoraPedidoService {

    public double calcularSubtotal(List<LineaPedido> lineas) {
        Objects.requireNonNull(lineas, "lineas");
        return lineas.stream().mapToDouble(LineaPedido::subtotalLinea).sum();
    }

    public double calcularImpuesto(double subtotal, double tasaImpuesto) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("subtotal no puede ser negativo");
        }
        if (tasaImpuesto < 0 || tasaImpuesto > 1) {
            throw new IllegalArgumentException("tasaImpuesto debe estar entre 0 y 1");
        }
        return subtotal * tasaImpuesto;
    }

    public double calcularTotal(double subtotal, double impuesto) {
        if (subtotal < 0 || impuesto < 0) {
            throw new IllegalArgumentException("montos no pueden ser negativos");
        }
        return subtotal + impuesto;
    }
}
