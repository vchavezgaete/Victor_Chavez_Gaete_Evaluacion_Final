package cl.iplacex.automatizacion.unit;

import cl.iplacex.automatizacion.model.LineaPedido;
import cl.iplacex.automatizacion.service.CalculadoraPedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraPedidoServiceTest {

    private final CalculadoraPedidoService calculadora = new CalculadoraPedidoService();

    @Test
    @DisplayName("Subtotal suma correctamente todas las líneas")
    void subtotalSumaLineas() {
        var lineas = List.of(
                new LineaPedido("A", 2, 1000),
                new LineaPedido("B", 1, 500)
        );
        assertEquals(2500.0, calculadora.calcularSubtotal(lineas), 1e-9);
    }

    @Test
    @DisplayName("Impuesto rechaza tasa fuera de rango")
    void impuestoRechazaTasaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularImpuesto(100, -0.01));
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularImpuesto(100, 1.5));
    }

    @Test
    @DisplayName("Total es subtotal más impuesto")
    void totalEsSuma() {
        assertEquals(1190.0, calculadora.calcularTotal(1000, 190), 1e-9);
    }
}
