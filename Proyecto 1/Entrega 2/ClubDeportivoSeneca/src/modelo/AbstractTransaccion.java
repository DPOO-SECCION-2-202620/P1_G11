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
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad debe ser positiva");
		}compras.add(new Compras(articulo, cantidad, articulo.getPrecio()));
	}

	public abstract double calcularTotal();
			
	public String getFecha() {
		return fecha;
	}
	protected double calcularSubtotal() {
		double sub = 0;
		for (Compras c : compras) {
			sub += c.getCantidad() * c.getPrecioXunidad();
		}return sub;
	}
	protected double calcularBase() {
		return Math.max(0, calcularSubtotal() - descuento);
	}
}
