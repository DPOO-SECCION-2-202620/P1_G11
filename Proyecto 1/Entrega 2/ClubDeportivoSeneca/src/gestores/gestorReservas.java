package gestores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import modelo.AbstractDeporte;
import modelo.AbstractPartido;
import modelo.AsistenciaEntreno;
import modelo.Cancha;
import modelo.ClubDeportivo;
import modelo.DepIndividual;
import modelo.Entrenador;
import modelo.Entrenamiento;
import modelo.Equipo;
import modelo.EstadoAsistencia;
import modelo.InscripcionDisciplina;
import modelo.Jugador;
import modelo.PartidoIndividual;
import modelo.Reserva;

public class gestorReservas {
	private ClubDeportivo club;
	public gestorReservas(ClubDeportivo club) {
		super();
		this.club = club;
	}
	public Reserva reservar(Jugador jugador, DepIndividual deporte, String fecha, int horaInicio, int horaFin,
			List<Jugador> jugadores, boolean llevaBebidaCaliente) {
		if (!jugador.estaActivo()) {
			throw new IllegalArgumentException("El jugador no esta activo");
		}if (buscarInscripcion(jugador, deporte) == null) {
			throw new IllegalArgumentException("El jugador no esta inscrito en la academia de ese deporte");
		}for (Jugador j : jugadores) {
			if (!j.estaActivo()) {
				throw new IllegalArgumentException(j.getNombre() + " no esta activo");
			}
		}if (!deporte.admiteJugadores(jugadores.size())) {
			throw new IllegalArgumentException("El deporte no admite esa cantidad de jugadores");
		}validarBloque(deporte, fecha, horaInicio, horaFin);
		Cancha can = club.asignarCancha(deporte, fecha, horaInicio, horaFin, jugadores.size());
		if (can == null) {
			throw new IllegalStateException("No hay cancha disponible en ese horario");
		}if (llevaBebidaCaliente && !can.permiteBebidaCaliente()) {
			club.liberarCancha(can, fecha, horaInicio, horaFin);
			throw new IllegalStateException("Esa cancha no permite bebidas calientes");
		}Reserva res = new Reserva(can, fecha, horaInicio, horaFin, jugadores);
		List<Reserva> reservas = club.getReservas();
		reservas.add(res);
		return res;
	}
	public void cancelarReserva(Reserva reserva) {
		if (!reserva.estaActiva()) {
			throw new IllegalStateException("La reserva ya esta cancelada");
		}reserva.cancelar();
		club.liberarCancha(reserva.getCancha(), reserva.getFecha(), reserva.getHoraInicio(), reserva.getHoraFin());
	}
	public PartidoIndividual programarPractica(List<Jugador> jugadores, DepIndividual deporte, String fecha,
			int horaInicio, int horaFin) {
		if (jugadores.isEmpty() || new HashSet<>(jugadores).size() != jugadores.size()) {
			throw new IllegalArgumentException("La lista de jugadores esta vacia o tiene repetidos");
		}boolean hayEnt = false;
		List<InscripcionDisciplina> inss = new ArrayList<>();
		for (Jugador j : jugadores) {
			if (!j.estaActivo()) {
				throw new IllegalArgumentException(j.getNombre() + " no esta activo");
			}InscripcionDisciplina ins = buscarInscripcion(j, deporte);
			if (ins == null) {
				throw new IllegalArgumentException(j.getNombre() + " no esta inscrito en la academia de ese deporte");
			}inss.add(ins);
			if (j.esTambienEntrenador()) {
				hayEnt = true;
			}
		}if (!hayEnt) {
			for (InscripcionDisciplina a : inss) {
				for (InscripcionDisciplina b : inss) {
					if (!a.getNivel().esAdyacente(b.getNivel())) {
						throw new IllegalArgumentException("Los jugadores no tienen niveles adyacentes");
					}
				}
			}
		}Reserva res = reservar(jugadores.get(0), deporte, fecha, horaInicio, horaFin, jugadores, false);
		PartidoIndividual parInd = new PartidoIndividual(fecha, deporte, res);
		List<AbstractPartido> partidos = club.getPartidos();
		partidos.add(parInd);
		return parInd;
	}
	public void registrarResultadoPractica(PartidoIndividual partido) {
		partido.actualizarRankings();
	}
	public List<Reserva> getReservas(String desde, String hasta) {
		if (desde.compareTo(hasta) > 0) {
			throw new IllegalArgumentException("El rango de fechas esta invertido");
		}List<Reserva> rta = new ArrayList<>();
		for (Reserva r : club.getReservas()) {
			if (r.getFecha().compareTo(desde) >= 0 && r.getFecha().compareTo(hasta) <= 0) {
				rta.add(r);
			}
		}return rta;
	}
	public Entrenamiento crearEntrenamiento(Entrenador entrenador, AbstractDeporte deporte, String fecha,
			int horaInicio, int horaFin, List<Jugador> convocados) {
		if (!entrenador.estaActivo()) {
			throw new IllegalArgumentException("El entrenador no esta activo");
		}if (new HashSet<>(convocados).size() != convocados.size()) {
			throw new IllegalArgumentException("Hay jugadores convocados repetidos");
		}for (Jugador j : convocados) {
			if (!j.estaActivo()) {
				throw new IllegalArgumentException(j.getNombre() + " no esta activo");
			}if (!pertenece(j, deporte)) {
				throw new IllegalArgumentException(j.getNombre() + " no pertenece al equipo ni a la academia de ese deporte");
			}
		}validarBloque(deporte, fecha, horaInicio, horaFin);
		Cancha can = club.asignarCancha(deporte, fecha, horaInicio, horaFin, convocados.size());
		if (can == null) {
			throw new IllegalStateException("No hay cancha disponible en ese horario");
		}Entrenamiento ent = new Entrenamiento(entrenador, can, fecha, horaInicio, horaFin);
		for (Jugador j : convocados) {
			ent.convocar(j);
		}List<Entrenamiento> entrenamientos = club.getEntrenamientos();
		entrenamientos.add(ent);
		return ent;
	}
	public void cancelarEntrenamiento(Entrenamiento entrenamiento) {
		if (!entrenamiento.estaActivo()) {
			throw new IllegalStateException("El entrenamiento ya esta cancelado");
		}entrenamiento.cancelar();
		club.liberarCancha(entrenamiento.getCancha(), entrenamiento.getFecha(), entrenamiento.getHoraInicio(), entrenamiento.getHoraFin());
	}
	public void registrarAsistencia(Entrenamiento entrenamiento, Jugador jugador, EstadoAsistencia estado) {
		if (!entrenamiento.estaActivo()) {
			throw new IllegalStateException("El entrenamiento esta cancelado");
		}entrenamiento.registrarAsistencia(jugador, estado);
	}
	public void registrarEvaluacion(Entrenamiento entrenamiento, Jugador jugador, int nota, String comentario) {
		if (!entrenamiento.estaActivo()) {
			throw new IllegalStateException("El entrenamiento esta cancelado");
		}entrenamiento.registrarEvaluacion(jugador, nota, comentario);
	}
	public List<Entrenamiento> getEntrenamientos(String desde, String hasta) {
		if (desde.compareTo(hasta) > 0) {
			throw new IllegalArgumentException("El rango de fechas esta invertido");
		}List<Entrenamiento> rta = new ArrayList<>();
		for (Entrenamiento e : club.getEntrenamientos()) {
			if (e.getFecha().compareTo(desde) >= 0 && e.getFecha().compareTo(hasta) <= 0) {
				rta.add(e);
			}
		}return rta;
	}
	public List<Entrenamiento> getEntrenamientosDe(Jugador jugador) {
		List<Entrenamiento> rta = new ArrayList<>();
		for (Entrenamiento e : club.getEntrenamientos()) {
			for (AsistenciaEntreno a : e.getConvocados()) {
				if (a.getJugador() == jugador) {
					rta.add(e);
				}
			}
		}return rta;
	}
	private void validarBloque(AbstractDeporte deporte, String fecha, int horaInicio, int horaFin) {
		if (fecha == null || fecha.trim().isEmpty()) {
			throw new IllegalArgumentException("Falta la fecha");
		}if (!club.bloqueValido(horaInicio, horaFin)) {
			throw new IllegalArgumentException("El horario no es valido o esta fuera del horario de atencion");
		}if (club.contarOcupadas(deporte, fecha, horaInicio, horaFin) >= deporte.getMaxSimultaneas()) {
			throw new IllegalStateException("Ya estan ocupadas todas las instalaciones permitidas de ese deporte");
		}
	}
	private InscripcionDisciplina buscarInscripcion(Jugador jugador, DepIndividual deporte) {
		for (InscripcionDisciplina ins : jugador.getInscripciones()) {
			if (ins.getDeporte() == deporte) {
				return ins;
			}
		}return null;
	}
	private boolean pertenece(Jugador jugador, AbstractDeporte deporte) {
		if (deporte instanceof DepIndividual) {
			return buscarInscripcion(jugador, (DepIndividual) deporte) != null;
		}for (Equipo e : club.getEquipos()) {
			if (e.getDeporte() == deporte && e.getInfo(jugador) != null) {
				return true;
			}
		}return false;
	}
}