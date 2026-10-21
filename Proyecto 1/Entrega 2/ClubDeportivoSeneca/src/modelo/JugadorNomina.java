package modelo;

import java.io.Serializable;

public class JugadorNomina implements Serializable {

	private static final long serialVersionUID = 1L;

	private Jugador jugador;

	private int amarillasAcumuladas;

	private boolean suspendido;

	public JugadorNomina(Jugador jugador) {
		this.jugador = jugador;
		this.amarillasAcumuladas = 0;
		this.suspendido = false;
	}

	public int getAmarillasAcumuladas() {
		return amarillasAcumuladas;
	}

	public boolean isSuspendido() {
		return suspendido;
	}

	public void setAmarillasAcumuladas(int amarillasAcumuladas) {
		this.amarillasAcumuladas = amarillasAcumuladas;
	}

	public void setSuspendido(boolean suspendido) {
		this.suspendido = suspendido;
	}
	public Jugador getJugador() {
		return jugador;
	}
}
