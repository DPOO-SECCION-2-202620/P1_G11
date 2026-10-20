package modelo;

import java.io.Serializable;

public abstract class AbstractDeporte implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	protected AbstractDeporte(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public abstract AbstractEstadisticas crearEstadisticas(AbstractPartido partido, RolJugador jugador);
}
