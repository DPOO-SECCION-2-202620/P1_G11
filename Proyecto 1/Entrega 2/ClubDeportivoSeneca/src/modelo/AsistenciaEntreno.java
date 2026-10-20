package modelo;

import java.io.Serializable;

public class AsistenciaEntreno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Jugador jugador;

	private EstadoAsistencia estado;

	private int nota;

	private String comentario;

	public AsistenciaEntreno(Jugador jugador) {
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
}
