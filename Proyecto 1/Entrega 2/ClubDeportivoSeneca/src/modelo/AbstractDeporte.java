package modelo;

import java.io.Serializable;

public abstract class AbstractDeporte implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private int maxSimultaneas;

	protected AbstractDeporte(String nombre, int maxSimultaneas) {
		this.nombre = nombre;
		this.maxSimultaneas = maxSimultaneas;
	}

	public String getNombre() {
		return nombre;
	}

	public int getMaxSimultaneas() {
		return maxSimultaneas;
	}

	public abstract AbstractEstadisticas crearEstadisticas();
}
