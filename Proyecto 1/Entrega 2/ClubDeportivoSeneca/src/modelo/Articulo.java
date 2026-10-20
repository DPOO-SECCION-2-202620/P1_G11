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
		}
		if (cantidad > cantidades.get(origen)) {
			throw new IllegalStateException("Cantidad a mover se excede de lo que se tiene");
		}

		int cant_org = cantidades.get(origen) - cantidad;
		int cant_dest = cantidades.get(destino) + cantidad;
		cantidades.put(origen, cant_org);
		cantidades.put(destino, cant_dest);

	}

	public void descontar(Ubicacion ubicacion, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalStateException("Cantidad a descontar invalida");
		}
		if (cantidad > cantidades.get(ubicacion)) {
			throw new IllegalStateException("Cantidad a descontar se excede de lo que se tiene");
		}
		int cant = cantidades.get(ubicacion) - cantidad;
		cantidades.put(ubicacion, cant);

	}

	public void reabastecer(Ubicacion ubicacion, int cantidad) {
		if (cantidad <= 0) {
			throw new IllegalStateException("Cantidad a reabastecer invalida");
		}
		int cant = cantidades.get(ubicacion) + cantidad;
		cantidades.put(ubicacion, cant);
	}
}
