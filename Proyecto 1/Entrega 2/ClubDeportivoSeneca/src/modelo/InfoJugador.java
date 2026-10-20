package modelo;

import java.io.Serializable;

public class InfoJugador implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolJugador jugador;

	private Equipo equipo;

	private String posicion;

	private int numeroCamisa;

	private boolean suspendido;

	public InfoJugador(RolJugador jugador, Equipo equipo, String posicion, int numeroCamisa) {
		this.jugador = jugador;
		this.equipo = equipo;
		this.posicion = posicion;
		this.numeroCamisa = numeroCamisa;
		this.suspendido = false;
	}

	public boolean isSuspendido() {
		return suspendido;
	}

	public void setSuspendido(boolean suspendido) {
		this.suspendido = suspendido;
	}
}
