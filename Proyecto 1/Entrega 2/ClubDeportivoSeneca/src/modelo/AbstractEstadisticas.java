package modelo;

import java.io.Serializable;

public abstract class AbstractEstadisticas implements Serializable {

	private static final long serialVersionUID = 1L;

	private AbstractPartido partido;

	private RolJugador jugador;

	protected AbstractEstadisticas(AbstractPartido partido, RolJugador jugador) {
		this.partido = partido;
		this.jugador = jugador;
	}
}
