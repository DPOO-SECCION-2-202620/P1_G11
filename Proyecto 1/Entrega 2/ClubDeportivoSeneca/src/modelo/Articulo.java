package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private String categoria;

	private double precio;

	private Map<Ubicacion, Integer> cantidades;

	public Articulo(String nombre, String categoria, double precio) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.cantidades = new HashMap<>();
	}

	public int getCantidad(Ubicacion ubicacion) {
		return cantidades.getOrDefault(ubicacion, 0);
	}

	public void mover(Ubicacion origen, Ubicacion destino, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalStateException("Cantidad a mover invalida");
		}if (origen == destino) {
			throw new IllegalStateException("El origen y el destino deben ser distintos");
		}if (cantidad > getCantidad(origen)) {
			throw new IllegalStateException("Cantidad a mover se excede de lo que se tiene");
		}cantidades.put(origen, getCantidad(origen) - cantidad);
		cantidades.put(destino, getCantidad(destino) + cantidad);
	}

	public void descontar(Ubicacion ubicacion, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalStateException("Cantidad a descontar invalida");
		}if (cantidad > getCantidad(ubicacion)) {
			throw new IllegalStateException("Cantidad a descontar se excede de lo que se tiene");
		}cantidades.put(ubicacion, getCantidad(ubicacion) - cantidad);
	}

	public void reabastecer(Ubicacion ubicacion, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalStateException("Cantidad a reabastecer invalida");
		}cantidades.put(ubicacion, getCantidad(ubicacion) + cantidad);
	}
	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}
	
}
