package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entrenamiento implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolEntrenador entrenador;

	private Cancha cancha;

	private String fecha;

	private int hora;

	private int duracion;

	private List<AsistenciaEntreno> convocados;

	public Entrenamiento(RolEntrenador entrenador, Cancha cancha, String fecha, int hora, int duracion) {
		this.entrenador = entrenador;
		this.cancha = cancha;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
		this.convocados = new ArrayList<>();
	}

	public List<AsistenciaEntreno> getConvocados() {
		return convocados;
	}

	public void convocar(RolJugador jugador) {
		// TODO: RF-ENT-01, D-20: crear su AsistenciaEntreno
		throw new UnsupportedOperationException("Pendiente");
	}

	public void registrarAsistencia(RolJugador jugador, EstadoAsistencia estado) {
		// TODO: RF-ENT-03
		throw new UnsupportedOperationException("Pendiente");
	}

	public void registrarEvaluacion(RolJugador jugador, int nota, String comentario) {
		// TODO: RF-ENT-04
		throw new UnsupportedOperationException("Pendiente");
	}

	public String getFecha() {
		return fecha;
	}

	public int getHora() {
		return hora;
	}

	public int getDuracion() {
		return duracion;
	}

	public RolEntrenador getEntrenador() {
		return entrenador;
	}

	public Cancha getCancha() {
		return cancha;
	}
}
