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

class ServicioPedidoFlujoIT {

    @Test
    @DisplayName("Suma varias líneas con IVA")
    void multiplesLineas() {
        var servicio = new PedidoService(
                new ValidacionPedidoService(),
                new CalculadoraPedidoService(),
                0.19
        );
        var pedido = new Pedido("P-99", "Empresa SA", List.of(
                new LineaPedido("Licencia", 1, 100000),
                new LineaPedido("Soporte", 3, 10000)
        ));
        var r = servicio.procesar(pedido);
        assertEquals(130000.0, r.subtotal(), 1e-6);
        assertEquals(24700.0, r.impuesto(), 1e-6);
        assertEquals(154700.0, r.total(), 1e-6);
    }

    @Test
    @DisplayName("Rechaza cliente vacío")
    void clienteVacio() {
        var servicio = new PedidoService(
                new ValidacionPedidoService(),
                new CalculadoraPedidoService(),
                0.19
        );
        var pedido = new Pedido("P-00", "   ", List.of(new LineaPedido("X", 1, 1)));
        assertThrows(IllegalArgumentException.class, () -> servicio.procesar(pedido));
    }
}
