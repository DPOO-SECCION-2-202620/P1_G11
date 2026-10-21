package gestores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.AbstractCompetencia;
import modelo.AbstractDeporte;
import modelo.AbstractEstadisticas;
import modelo.AbstractPartido;
import modelo.AbstractPartidoEquipos;
import modelo.Categoria;
import modelo.ClubDeportivo;
import modelo.DepConjunto;
import modelo.DepIndividual;
import modelo.Equipo;
import modelo.EstadisticasFutbol;
import modelo.InfoJugador;
import modelo.InscripcionDisciplina;
import modelo.Jugador;
import modelo.JugadorNomina;
import modelo.ParticipacionCompe;
import modelo.PartidoCompetencia;
import modelo.PartidoInterno;
import modelo.Usuario;

public class gestorDeportivo {
	private ClubDeportivo club;

	public gestorDeportivo(ClubDeportivo club) {
		super();
		this.club = club;


	}
	
	public Equipo registrarEquipo(String nombre, DepConjunto deporte, Categoria categoria, int maxJugadores) {
		if (nombre == null || nombre.trim().isEmpty() || deporte == null || categoria == null) {
			throw new IllegalArgumentException("Faltan datos del equipo");
		}if (maxJugadores <= 0) {
			throw new IllegalArgumentException("El maximo de jugadores debe ser positivo");
		}Equipo equipo = new Equipo(nombre,deporte,categoria,maxJugadores);
		List<Equipo> equipos = club.getEquipos();
		equipos.add(equipo);
		return equipo;
	}
	
	public void darDeBajaEquipo(Equipo equipo) {
		for (AbstractCompetencia compe : club.getCompetencias()) {
			if (compe.getParticipacion(equipo) != null) {
				throw new IllegalStateException("El equipo esta en una competencia en curso");
			}
		}for (AbstractPartido p : club.getPartidos()) {
			if (p instanceof PartidoInterno) {
				PartidoInterno pi = (PartidoInterno) p;
				if ((pi.getLocal() == equipo || pi.getVisitante() == equipo) && !pi.isResultadoRegistrado()) {
					throw new IllegalStateException("El equipo tiene partidos por disputar");
				}
			}
		}
		equipo.darDeBaja();
	}

	public void inscribirEnEquipo(Jugador jugador, Equipo equipo, String posicion, int camiseta) {
		if (!jugador.estaActivo()){
			throw new IllegalArgumentException("El jugador no está activo");
		}if (!equipo.isActivo()){
			throw new IllegalStateException("El equipo no está activo");
		}List<Equipo> equipos = club.getEquipos();
		for (Equipo e : equipos) {
			if (e != equipo && e.getDeporte() == equipo.getDeporte() && e.getInfo(jugador) != null) {
				throw new IllegalArgumentException("El jugador ya está en otro equipo de ese deporte");
			}
		}
		equipo.inscribirJugador(jugador, posicion, camiseta);
	}

public void inscribirEnAcademia(Jugador jugador, DepIndividual deporte) {
	if (!jugador.estaActivo()) {
		throw new IllegalArgumentException("El jugador no esta activo");
	}if (buscarInscripcion(jugador, deporte) != null) {
		throw new IllegalArgumentException("El jugador ya esta inscrito en esa academia");
	}jugador.agregarInscripcion(new InscripcionDisciplina(jugador, deporte));
}
public void retirarseDeEquipo(Jugador jugador, Equipo equipo) {
	equipo.retirarJugador(jugador);
}
public void retirarseDeAcademia(Jugador jugador, DepIndividual deporte) {
	InscripcionDisciplina ins = buscarInscripcion(jugador, deporte);
	if (ins == null) {
		throw new IllegalArgumentException("El jugador no esta inscrito en esa academia");} 
	jugador.retirarInscripcion(ins);
}
public List<InscripcionDisciplina> getRanking(DepIndividual deporte) {
	List<InscripcionDisciplina> resultado = new ArrayList<>();
	for (Usuario u : club.getUsuarios()) {
		if (u instanceof Jugador) {
			InscripcionDisciplina ins = buscarInscripcion((Jugador) u, deporte);
			if (ins != null) {
				resultado.add(ins);
			}
		}
	}resultado.sort((a, b) -> b.getRanking() - a.getRanking());
	return resultado;
}
public void crearCompetencia(AbstractCompetencia competencia) {
	for (AbstractCompetencia c : club.getCompetencias()) {
		if (c.getNombre().equals(competencia.getNombre())) {
			throw new IllegalArgumentException("Ya existe una competencia con ese nombre");
		}
	}List<AbstractCompetencia> competencias = club.getCompetencias();
    competencias.add(competencia);
}
public void inscribirEquipoCompetencia(AbstractCompetencia competencia, Equipo equipo, List<Jugador> nomina) {
	competencia.inscribirEquipo(equipo, nomina); // deporte, categoria, repetido y nomina minima los valida la competencia
}
public PartidoCompetencia programarPartidoCompetencia(AbstractCompetencia competencia, Equipo equipo, String rival,
		String lugar, String fecha) {
	if (competencia.getParticipacion(equipo) == null){
		throw new IllegalArgumentException("El equipo no esta inscrito en la competencia");
	}if (rival == null || rival.trim().isEmpty() || fecha == null || fecha.trim().isEmpty()){
		throw new IllegalArgumentException("Faltan datos del partido");
	}PartidoCompetencia partido = new PartidoCompetencia(fecha,equipo.getDeporte(),equipo,rival,lugar,null);
	competencia.agregarPartido(partido);
	club.getPartidos().add(partido);
	return partido;
}
public PartidoInterno programarPartidoInterno(Equipo local, Equipo visitante, String fecha) {
	if (local == visitante){
		throw new IllegalArgumentException("Un equipo no puede jugar contra si mismo");
	}if (local.getDeporte() != visitante.getDeporte()) {
		throw new IllegalArgumentException("Los equipos deben ser del mismo deporte");
	}PartidoInterno partido = new PartidoInterno(fecha, local.getDeporte(), local, visitante);
	club.getPartidos().add(partido);
	return partido;
}
public void registrarAlineacion(AbstractPartidoEquipos partido, Equipo equipo, List<Jugador> jugadores) {
	for (Jugador j : jugadores) {
		if (!j.estaActivo()) {
			throw new IllegalArgumentException(j.getNombre() + " no esta activo");
		}
	}if (partido instanceof PartidoCompetencia) {
		PartidoCompetencia pc = (PartidoCompetencia) partido;
		AbstractCompetencia comp = competenciaDe(pc);               
		if (comp == null) {
		      throw new IllegalStateException("El partido no pertenece a ninguna competencia");
		  }
		Equipo equipoClub = pc.getEquipoClub();                 
		ParticipacionCompe part = comp.getParticipacion(equipoClub);
		for (Jugador j : jugadores) {
			JugadorNomina jn = buscarNomina(part, j);
			if (jn == null) {
				throw new IllegalArgumentException(j.getNombre() + " no esta en la nomina de la competencia");
			}if (jn.isSuspendido()) {
				throw new IllegalArgumentException(j.getNombre() + " esta suspendido en la competencia");
			}
		}
	}partido.registrarAlineacion(equipo, jugadores);
}
public void registrarResultadoPartido(AbstractPartidoEquipos partido, int local, int visitante) {
	partido.registrarResultado(local, visitante); // valida (marcador, empate, ya registrado) y luego guarda
	if (partido instanceof PartidoCompetencia) {
		PartidoCompetencia pc = (PartidoCompetencia) partido;
		AbstractCompetencia comp = competenciaDe(pc);
		ParticipacionCompe part = comp == null ? null : comp.getParticipacion(pc.getEquipoClub());
		if (part != null) {
			DepConjunto dep = (DepConjunto) pc.getDeporte();
			if (local > visitante) {
				part.sumarPuntos(dep.getPuntosVictoria());
			}else if (local == visitante) {
				part.sumarPuntos(dep.getPuntosEmpate());
			}else {
				part.sumarPuntos(dep.getPuntosDerrota());
			}
		}
	}
	aplicarSuspensiones(partido);
}
private void aplicarSuspensiones(AbstractPartidoEquipos partido) {
	for (Map.Entry<Jugador, AbstractEstadisticas> par : partido.getEstadisticas().entrySet()) {
		if ((par.getValue() instanceof EstadisticasFutbol)) {
		
		Jugador j = par.getKey();
		int amarillas = ((EstadisticasFutbol) par.getValue()).getAmarillas();
		int rojas = ((EstadisticasFutbol) par.getValue()).getRojas();
		if(partido instanceof PartidoInterno) {
			PartidoInterno pi = (PartidoInterno) partido;
			InfoJugador info = pi.getLocal().getInfo(j) != null ? pi.getLocal().getInfo(j) : pi.getVisitante().getInfo(j);
			if (info != null && (amarillas >= 2 || rojas >= 1)) {
				info.setSuspendido(true);
			}
		}else if (partido instanceof PartidoCompetencia) {
			PartidoCompetencia pc = (PartidoCompetencia) partido;
			AbstractCompetencia comp = competenciaDe(pc);
			ParticipacionCompe part = comp == null ? null : comp.getParticipacion(pc.getEquipoClub());
			JugadorNomina jn = part == null ? null : buscarNomina(part, j);
			if (jn != null) {
				jn.setAmarillasAcumuladas(jn.getAmarillasAcumuladas() + amarillas);
				if (jn.getAmarillasAcumuladas() >= 2 || rojas >= 1) {
					jn.setSuspendido(true);
				}
			}
		}
	}
	}
}
public List<Equipo> getEquiposDe(Jugador jugador) {
	List<Equipo> resultado = new ArrayList<>();
	for (Equipo e : club.getEquipos()) {
		if (e.getInfo(jugador) != null) {
			resultado.add(e);
		}
	}
	return resultado;
}
public List<AbstractPartido> getPartidos(String desde, String hasta) {
	if (desde.compareTo(hasta) > 0) {
		throw new IllegalArgumentException("El rango de fechas esta invertido");
	}List<AbstractPartido> resultado = new ArrayList<>();
	for (AbstractPartido p : club.getPartidos()) {
		if (p.getFecha().compareTo(desde) >= 0 && p.getFecha().compareTo(hasta) <= 0) {
			resultado.add(p);
		}
	}
	return resultado;
}
public List<AbstractEstadisticas> getEstadisticasDe(Jugador jugador, AbstractDeporte deporte) {
	List<AbstractEstadisticas> resultado = new ArrayList<>();
	for (AbstractPartido p : club.getPartidos()) {
		if (p.getDeporte() == deporte && p.getEstadisticas().get(jugador) != null) {
			resultado.add(p.getEstadisticas().get(jugador));
		}
	}
	return resultado;
}

private InscripcionDisciplina buscarInscripcion(Jugador jugador, DepIndividual deporte) {
	for (InscripcionDisciplina ins : jugador.getInscripciones()) {
		if (ins.getDeporte() == deporte) {
			return ins;
		}
	}return null;
}
private AbstractCompetencia competenciaDe(PartidoCompetencia partido) {
	for (AbstractCompetencia c : club.getCompetencias()) {
		if (c.getPartidos().contains(partido)) {
			return c;
		}
	}
	return null;
}
private JugadorNomina buscarNomina(ParticipacionCompe participacion, Jugador jugador) {
	for (JugadorNomina jn : participacion.getNomina()) {
		if (jn.getJugador() == jugador) {
			return jn;
		}
	}
	return null;
}

}
