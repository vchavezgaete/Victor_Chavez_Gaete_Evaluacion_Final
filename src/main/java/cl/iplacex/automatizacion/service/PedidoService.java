package cl.iplacex.automatizacion.service;

import cl.iplacex.automatizacion.model.Pedido;

import java.util.Objects;

/**
 * Orquesta validación y cálculo de totales para un pedido.
 */
public class PedidoService {

    private final ValidacionPedidoService validacion;
    private final CalculadoraPedidoService calculadora;
    private final double tasaImpuesto;

    public PedidoService(ValidacionPedidoService validacion,
                         CalculadoraPedidoService calculadora,
                         double tasaImpuesto) {
        this.validacion = Objects.requireNonNull(validacion, "validacion");
        this.calculadora = Objects.requireNonNull(calculadora, "calculadora");
        this.tasaImpuesto = tasaImpuesto;
    }

    public ResumenPedido procesar(Pedido pedido) {
        validacion.validar(pedido);
        double subtotal = calculadora.calcularSubtotal(pedido.getLineas());
        double impuesto = calculadora.calcularImpuesto(subtotal, tasaImpuesto);
        double total = calculadora.calcularTotal(subtotal, impuesto);
        return new ResumenPedido(pedido.getId(), subtotal, impuesto, total);
    }

    public record ResumenPedido(String pedidoId, double subtotal, double impuesto, double total) {
    }
}
