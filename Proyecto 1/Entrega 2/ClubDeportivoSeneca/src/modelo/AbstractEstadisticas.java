package modelo;

import java.io.Serializable;

public abstract class AbstractEstadisticas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Jugador jugador;

	protected AbstractEstadisticas() {
		this.jugador = null;
	}
}
