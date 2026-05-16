package cl.iplacex.automatizacion.service;

import cl.iplacex.automatizacion.model.LineaPedido;
import cl.iplacex.automatizacion.model.Pedido;

import java.util.Objects;

public class ValidacionPedidoService {

    public void validar(Pedido pedido) {
        Objects.requireNonNull(pedido, "pedido");
        if (pedido.getCliente().isBlank()) {
            throw new IllegalArgumentException("cliente obligatorio");
        }
        if (pedido.getLineas().isEmpty()) {
            throw new IllegalArgumentException("el pedido debe tener al menos una línea");
        }
        for (LineaPedido linea : pedido.getLineas()) {
            if (linea.getCantidad() <= 0) {
                throw new IllegalArgumentException("cantidad debe ser mayor a cero: " + linea.getProducto());
            }
            if (linea.getPrecioUnitario() < 0) {
                throw new IllegalArgumentException("precio no puede ser negativo: " + linea.getProducto());
            }
        }
    }
}
