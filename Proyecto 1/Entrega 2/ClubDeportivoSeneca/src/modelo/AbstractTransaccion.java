package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;

	private double descuento;

	private int puntosRedimidos;

	private List<Compras> compras;

	protected AbstractTransaccion(String fecha, double descuento, int puntosRedimidos) {
		this.fecha = fecha;
		this.descuento = descuento;
		this.puntosRedimidos = puntosRedimidos;
		this.compras = new ArrayList<>();
	}

	public List<Compras> getCompras() {
		return compras;
	}

	public void agregarCompra(Articulo articulo, int cantidad) {
		// TODO: D-22: crear la Compras con el precio actual
		throw new UnsupportedOperationException("Pendiente");
	}

	public abstract double calcularTotal();
}
