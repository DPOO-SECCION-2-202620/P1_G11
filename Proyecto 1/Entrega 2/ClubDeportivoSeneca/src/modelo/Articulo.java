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
		// TODO: RF-ADM-07/08, D-21: validar cantidad suficiente en origen
		throw new UnsupportedOperationException("Pendiente");
	}

	public void descontar(Ubicacion ubicacion, int cantidad) {
		// TODO: D-21: al vender
		throw new UnsupportedOperationException("Pendiente");
	}
}
