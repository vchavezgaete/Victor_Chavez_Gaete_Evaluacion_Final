package cl.iplacex.automatizacion.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Pedido con identificador, cliente y líneas.
 */
public final class Pedido {

    private final String id;
    private final String cliente;
    private final List<LineaPedido> lineas;

    public Pedido(String id, String cliente, List<LineaPedido> lineas) {
        this.id = Objects.requireNonNull(id, "id");
        this.cliente = Objects.requireNonNull(cliente, "cliente");
        this.lineas = List.copyOf(new ArrayList<>(Objects.requireNonNull(lineas, "lineas")));
    }

    public String getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public List<LineaPedido> getLineas() {
        return Collections.unmodifiableList(lineas);
    }
}
