package modelo;

import java.io.Serializable;

public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	private Articulo articulo;

	private int cantidad;

	private double precioXunidad;

	public Compra(Articulo articulo, int cantidad, double precioXunidad) {
		this.articulo = articulo;
		this.cantidad = cantidad;
		this.precioXunidad = precioXunidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecioXunidad() {
		return precioXunidad;
	}
}
