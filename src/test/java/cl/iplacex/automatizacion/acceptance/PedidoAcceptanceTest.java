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

class PedidoAcceptanceTest {

    private static PedidoService servicio() {
        return new PedidoService(new ValidacionPedidoService(), new CalculadoraPedidoService(), 0.19);
    }

    @Test
    @DisplayName("Pedido válido con IVA")
    void pedidoConsumidorConIva() {
        var pedido = new Pedido("WEB-1", "Consumidor final", List.of(
                new LineaPedido("Auriculares", 1, 19990),
                new LineaPedido("Cable USB", 2, 4990)
        ));
        var resumen = servicio().procesar(pedido);
        assertEquals(29970.0, resumen.subtotal(), 1e-6);
        assertEquals(5694.3, resumen.impuesto(), 1e-6);
        assertEquals(35664.3, resumen.total(), 1e-6);
    }

    @Test
    @DisplayName("Rechaza precio negativo")
    void rechazoPrecioNegativo() {
        var pedido = new Pedido("WEB-2", "Cliente", List.of(new LineaPedido("Promo", 1, -1)));
        assertThrows(IllegalArgumentException.class, () -> servicio().procesar(pedido));
    }
}
