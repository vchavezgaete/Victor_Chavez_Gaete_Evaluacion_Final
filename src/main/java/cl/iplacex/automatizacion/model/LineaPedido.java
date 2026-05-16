package cl.iplacex.automatizacion.model;

import java.util.Objects;

/**
 * Línea de un pedido con cantidad y precio unitario.
 */
public final class LineaPedido {

    private final String producto;
    private final int cantidad;
    private final double precioUnitario;

    public LineaPedido(String producto, int cantidad, double precioUnitario) {
        this.producto = Objects.requireNonNull(producto, "producto");
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double subtotalLinea() {
        return cantidad * precioUnitario;
    }
}
