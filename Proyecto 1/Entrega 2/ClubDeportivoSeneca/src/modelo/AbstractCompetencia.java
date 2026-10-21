package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCompetencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private String organizador;

	private Categoria categoria;

	private int temporada;

	private int minimoJugadoresNomina;

	private DepConjunto deporte;

	private List<ParticipacionCompe> participaciones;

	private List<PartidoCompetencia> partidos;

	protected AbstractCompetencia(String nombre, String organizador, Categoria categoria, int temporada, int minimoJugadoresNomina, DepConjunto deporte) {
		this.nombre = nombre;
		this.organizador = organizador;
		this.categoria = categoria;
		this.temporada = temporada;
		this.minimoJugadoresNomina = minimoJugadoresNomina;
		this.deporte = deporte;
		this.participaciones = new ArrayList<>();
		this.partidos = new ArrayList<>();
	}

	public void inscribirEquipo(Equipo equipo, List<Jugador> nomina) {
		if (equipo.getDeporte() != deporte) {
			throw new IllegalArgumentException("El equipo no es del deporte de la competencia");
		}if (equipo.getCategoria() != categoria) {
			throw new IllegalArgumentException("El equipo no es de la categoria de la competencia");
		}if (getParticipacion(equipo) != null) {
			throw new IllegalArgumentException("El equipo ya esta inscrito en la competencia");
		}if (nomina.size() < minimoJugadoresNomina) {
			throw new IllegalArgumentException("La nomina no tiene el minimo de jugadores");
		}for (Jugador j : nomina) {
			if (equipo.getInfo(j) == null) {
				throw new IllegalArgumentException(j.getNombre() + " no pertenece al equipo");
			}
		}ParticipacionCompe par = new ParticipacionCompe(equipo);
		for (Jugador j : nomina) {
			par.getNomina().add(new JugadorNomina(j));
		}participaciones.add(par);
	}

	public void agregarPartido(PartidoCompetencia partido) {
		partidos.add(partido);
	}

	public ParticipacionCompe getParticipacion(Equipo equipo) {
		for (ParticipacionCompe p : participaciones) {
			if (p.getEquipo() == equipo) {
				return p;
			}
		}return null;
	}

	public Jugador getGoleador() {
		Map<Jugador, Integer> goles = new HashMap<>();
		Jugador mej = null;
		int max = 0;
		for (PartidoCompetencia p : partidos) {
			for (Map.Entry<Jugador, AbstractEstadisticas> par : p.getEstadisticas().entrySet()) {
				if (par.getValue() instanceof EstadisticasFutbol) {
					int tot = goles.getOrDefault(par.getKey(), 0) + ((EstadisticasFutbol) par.getValue()).getGoles();
					goles.put(par.getKey(), tot);
					if (tot > max) {
						max = tot;
						mej = par.getKey();
					}
				}
			}
		}return mej;
	}
	public String getNombre() {
		return nombre;
	}
	public List<PartidoCompetencia> getPartidos() {
		return partidos;
	}	
}
