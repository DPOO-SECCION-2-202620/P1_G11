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
		this.equipos = new ArrayList<>();
		this.canchas = new ArrayList<>();
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
}
