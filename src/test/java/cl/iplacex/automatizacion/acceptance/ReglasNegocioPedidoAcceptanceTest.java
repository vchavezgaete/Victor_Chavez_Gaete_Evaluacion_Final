package cl.iplacex.automatizacion.acceptance;

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

class ReglasNegocioPedidoAcceptanceTest {

    @Test
    @DisplayName("Pedido corporativo multiproducto")
    void pedidoCorporativo() {
        var servicio = new PedidoService(
                new ValidacionPedidoService(),
                new CalculadoraPedidoService(),
                0.19
        );
        var pedido = new Pedido("CORP-500", "Inversiones Norte", List.of(
                new LineaPedido("Servidor", 1, 2500000),
                new LineaPedido("Instalación", 8, 45000)
        ));
        var r = servicio.procesar(pedido);
        assertEquals(2860000.0, r.subtotal(), 1e-6);
        assertEquals(543400.0, r.impuesto(), 1e-6);
        assertEquals(3403400.0, r.total(), 1e-6);
    }

    @Test
    @DisplayName("Rechaza cantidad cero")
    void cantidadCeroNoVendible() {
        var servicio = new PedidoService(
                new ValidacionPedidoService(),
                new CalculadoraPedidoService(),
                0.19
        );
        var pedido = new Pedido("POS-3", "Caja 2", List.of(new LineaPedido("SKU-1", 0, 1000)));
        assertThrows(IllegalArgumentException.class, () -> servicio.procesar(pedido));
    }
}
