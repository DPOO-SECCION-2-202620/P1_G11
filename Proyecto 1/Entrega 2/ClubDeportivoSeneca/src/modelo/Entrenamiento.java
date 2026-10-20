package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entrenamiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Entrenador entrenador;

	private Cancha cancha;

	private String fecha;

	private int horaInicio;

	private int horaFin;

	private boolean activo;

	private List<AsistenciaEntreno> convocados;

	public Entrenamiento(Entrenador entrenador, Cancha cancha, String fecha, int horaInicio, int horaFin) {
		this.entrenador = entrenador;
		this.cancha = cancha;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.activo = true;
		this.convocados = new ArrayList<>();
	}

	public List<AsistenciaEntreno> getConvocados() {
		return convocados;
	}

	public void convocar(Jugador jugador) {
		// TODO: RF-ENT-01, D-20: crear su AsistenciaEntreno
		throw new UnsupportedOperationException("Pendiente");
	}

	public void registrarAsistencia(Jugador jugador, EstadoAsistencia estado) {
		// TODO: RF-ENT-03
		throw new UnsupportedOperationException("Pendiente");
	}

	public void registrarEvaluacion(Jugador jugador, int nota, String comentario) {
		// TODO: RF-ENT-04
		throw new UnsupportedOperationException("Pendiente");
	}

	public void cancelar() {
		activo = false;
	}

	public boolean estaActivo() {
		return activo;
	}
}
