package cl.iplacex.automatizacion.unit;

import cl.iplacex.automatizacion.model.LineaPedido;
import cl.iplacex.automatizacion.model.Pedido;
import cl.iplacex.automatizacion.service.ValidacionPedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidacionPedidoServiceTest {

    private final ValidacionPedidoService validacion = new ValidacionPedidoService();

    @Test
    @DisplayName("Pedido válido no lanza excepción")
    void pedidoValidoPasa() {
        var pedido = new Pedido("P-1", "Cliente", List.of(new LineaPedido("X", 1, 10)));
        assertDoesNotThrow(() -> validacion.validar(pedido));
    }

    @Test
    @DisplayName("Pedido sin líneas es rechazado")
    void pedidoSinLineasRechazado() {
        var pedido = new Pedido("P-1", "Cliente", List.of());
        assertThrows(IllegalArgumentException.class, () -> validacion.validar(pedido));
    }
}
