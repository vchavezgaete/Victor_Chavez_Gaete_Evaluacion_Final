package cl.iplacex.automatizacion;

import cl.iplacex.automatizacion.model.LineaPedido;
import cl.iplacex.automatizacion.model.Pedido;
import cl.iplacex.automatizacion.service.CalculadoraPedidoService;
import cl.iplacex.automatizacion.service.PedidoService;
import cl.iplacex.automatizacion.service.ValidacionPedidoService;

import java.util.List;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
        var validacion = new ValidacionPedidoService();
        var calculadora = new CalculadoraPedidoService();
        var pedidoService = new PedidoService(validacion, calculadora, 0.19);

        var lineas = List.of(
                new LineaPedido("Teclado", 1, 45000),
                new LineaPedido("Mouse", 2, 15000)
        );
        var pedido = new Pedido("P-001", "Ana Pérez", lineas);

        var resumen = pedidoService.procesar(pedido);
        System.out.println("Pedido " + resumen.pedidoId());
        System.out.printf("Subtotal: %.2f%n", resumen.subtotal());
        System.out.printf("IVA: %.2f%n", resumen.impuesto());
        System.out.printf("Total: %.2f%n", resumen.total());
    }
}
