package cl.iplacex.automatizacion.integration;

import cl.iplacex.automatizacion.model.LineaPedido;
import cl.iplacex.automatizacion.model.Pedido;
import cl.iplacex.automatizacion.service.CalculadoraPedidoService;
import cl.iplacex.automatizacion.service.PedidoService;
import cl.iplacex.automatizacion.service.ValidacionPedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PedidoIntegrationIT {

    @Test
    @DisplayName("Procesa pedido válido")
    void procesarPedidoValidoIntegraServicios() {
        var validacion = new ValidacionPedidoService();
        var calculadora = new CalculadoraPedidoService();
        var servicio = new PedidoService(validacion, calculadora, 0.19);

        var pedido = new Pedido("P-10", "Luis", List.of(new LineaPedido("Item", 2, 5000)));
        var resumen = servicio.procesar(pedido);

        assertEquals(10000.0, resumen.subtotal(), 1e-9);
        assertEquals(1900.0, resumen.impuesto(), 1e-9);
        assertEquals(11900.0, resumen.total(), 1e-9);
    }

    @Test
    @DisplayName("Rechaza pedido inválido")
    void pedidoInvalidoNoCalculaTotal() {
        var servicio = new PedidoService(new ValidacionPedidoService(), new CalculadoraPedidoService(), 0.19);
        var pedido = new Pedido("P-11", "María", List.of(new LineaPedido("Item", 0, 100)));

        assertThrows(IllegalArgumentException.class, () -> servicio.procesar(pedido));
    }
}
