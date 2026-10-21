package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClubDeportivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int horaApertura;

	private int horaCierre;

	private List<Usuario> usuarios;

	private List<AbstractDeporte> deportes;
	private List<Equipo> equipos;
	private List<Cancha> canchas;
	private List<Reserva> reservas;
	private List<Entrenamiento> entrenamientos;
	private List<AbstractPartido> partidos;
	private List<AbstractCompetencia> competencias;
	private List<Articulo> articulos;
	private List<SugerenciaArticulo> sugerencias;
	private List<TurnoEmpleado> turnos;
	private List<SolicitudCambioTurno> solicitudes;

	public ClubDeportivo(int horaApertura, int horaCierre) {
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.usuarios = new ArrayList<>();
		this.deportes = new ArrayList<>();
		this.equipos = new ArrayList<Equipo>();
		this.canchas = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.entrenamientos = new ArrayList<>();
		this.partidos = new ArrayList<>();
		this.competencias = new ArrayList<>();
		this.articulos = new ArrayList<>();
		this.sugerencias = new ArrayList<>();
		this.turnos = new ArrayList<>();
		this.solicitudes = new ArrayList<>();
	}

	public int getHoraApertura() {
		return horaApertura;
	}

	public int getHoraCierre() {
		return horaCierre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<AbstractDeporte> getDeportes() {
		return deportes;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public List<Cancha> getCanchas() {
		return canchas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}

	public List<AbstractPartido> getPartidos() {
		return partidos;
	}

	public List<AbstractCompetencia> getCompetencias() {
		return competencias;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public List<SugerenciaArticulo> getSugerencias() {
		return sugerencias;
	}

	public List<TurnoEmpleado> getTurnos() {
		return turnos;
	}

	public List<SolicitudCambioTurno> getSolicitudes() {
		return solicitudes;
	}

	public Cancha asignarCancha(AbstractDeporte deporte, String fecha, int horaInicio, int horaFin, int cantidadJugadores) {
			if (contarOcupadas(deporte, fecha, horaInicio, horaFin) >= deporte.getMaxSimultaneas()) {
		return null;
	}
	for (Cancha c : canchas) {
		if (c.getDeporte() == deporte && c.tieneCapacidadPara(cantidadJugadores) && c.estaDisponible(fecha, horaInicio, horaFin)) {
			c.ocupar(fecha, horaInicio, horaFin);
			return c;
		}
	}
	return null; 
}	

	public void liberarCancha(Cancha cancha, String fecha, int horaInicio, int horaFin) {
		cancha.liberar(fecha, horaInicio, horaFin);
}

	public int contarOcupadas(AbstractDeporte deporte, String fecha, int horaInicio, int horaFin) {
		int ocupadas = 0;
	for (Cancha c : canchas) {
		if (c.getDeporte() == deporte && !c.estaDisponible(fecha, horaInicio, horaFin)) {
			ocupadas++;
		}
	}
	return ocupadas;
}


	public boolean bloqueValido(int horaInicio, int horaFin) {
		return horaInicio < horaFin && horaInicio >= horaApertura && horaFin <= horaCierre;
}

	
}
