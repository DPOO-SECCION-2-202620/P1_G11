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
		convocados.add(new AsistenciaEntreno(jugador));
	}

	public void registrarAsistencia(Jugador jugador, EstadoAsistencia estado) {
		AsistenciaEntreno player = buscar(jugador);
		player.setEstado(estado);
	}

	public void registrarEvaluacion(Jugador jugador, int nota, String comentario) {
		AsistenciaEntreno player = buscar(jugador);
		player.setEvaluacion(nota, comentario);
	}

	public void cancelar() {
		activo = false;
	}

	public boolean estaActivo() {
		return activo;
	}

	public String getFecha() {
		return fecha;
	}

	public Cancha getCancha() {
		return cancha;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}
	private AsistenciaEntreno buscar(Jugador jugador) {
		for (AsistenciaEntreno a : convocados) {
			if (a.getJugador() == jugador) {
				return a;
			}
		}throw new IllegalArgumentException("El jugador no esta convocado a este entrenamiento");
	}
	
}
