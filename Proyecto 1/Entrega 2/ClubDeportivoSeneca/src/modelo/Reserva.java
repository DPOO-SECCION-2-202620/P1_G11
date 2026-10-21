package modelo;

import java.io.Serializable;
import java.util.List;

public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cancha cancha;

	private String fecha;

	private int horaInicio;

	private int horaFin;

	private int cantidadJugadores;

	private boolean activa;

	private List<Jugador> jugadores;

	public Reserva(Cancha cancha, String fecha, int horaInicio, int horaFin, List<Jugador> jugadores) {
		this.cancha = cancha;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.cantidadJugadores = jugadores.size();
		this.activa = true;
		this.jugadores = jugadores;
	}

	public void cancelar() {
		activa = false;
	}

	public boolean estaActiva() {
		return activa;
	}

	public Cancha getCancha() {
		return cancha;
	}

	public String getFecha() {
		return fecha;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}
	
}
