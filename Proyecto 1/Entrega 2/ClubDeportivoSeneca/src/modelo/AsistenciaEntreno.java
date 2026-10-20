package modelo;

import java.io.Serializable;

public class AsistenciaEntreno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Entrenamiento entrenamiento;

	private RolJugador jugador;

	private EstadoAsistencia estado;

	private int nota;

	private String comentario;

	public AsistenciaEntreno(Entrenamiento entrenamiento, RolJugador jugador) {
		this.entrenamiento = entrenamiento;
		this.jugador = jugador;
		this.estado = null;
		this.nota = 0;
		this.comentario = null;
	}

	public EstadoAsistencia getEstado() {
		return estado;
	}

	public void setEstado(EstadoAsistencia estado) {
		this.estado = estado;
	}

	public void setEvaluacion(int nota, String comentario) {
		this.nota = nota;
		this.comentario = comentario;
	}

	public RolJugador getJugador() {
		return jugador;
	}

	public int getNota() {
		return nota;
	}

	public String getComentario() {
		return comentario;
	}
}
