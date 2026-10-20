package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Persona comprador;

	private String fecha;

	private double descuento;

	private int puntosRedimidos;

	private List<Compra> compras;

	protected AbstractTransaccion(Persona comprador, String fecha, double descuento, int puntosRedimidos) {
		this.comprador = comprador;
		this.fecha = fecha;
		this.descuento = descuento;
		this.puntosRedimidos = puntosRedimidos;
		this.compras = new ArrayList<>();
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void agregarCompra(Articulo articulo, int cantidad) {
		// TODO: D-22: crear la Compra con el precio actual
		throw new UnsupportedOperationException("Pendiente");
	}

	public abstract double calcularTotal();

	public Persona getComprador() {
		return comprador;
	}

	public String getFecha() {
		return fecha;
	}

	public double getDescuento() {
		return descuento;
	}

	public int getPuntosRedimidos() {
		return puntosRedimidos;
	}
}
