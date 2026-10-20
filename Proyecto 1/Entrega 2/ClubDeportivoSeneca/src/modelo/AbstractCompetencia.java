package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		// TODO: RN-24. En este UML Equipo no tiene getCategoria/getDeporte ni ParticipacionCompe getEquipo
		throw new UnsupportedOperationException("Pendiente");
	}

	public void agregarPartido(PartidoCompetencia partido) {
		partidos.add(partido);
	}

	public ParticipacionCompe getParticipacion(Equipo equipo) {
		// TODO: en este UML ParticipacionCompe no tiene getEquipo
		throw new UnsupportedOperationException("Pendiente");
	}

	public Jugador getGoleador() {
		// TODO: D-12: en este UML las estadisticas no tienen getters
		throw new UnsupportedOperationException("Pendiente");
	}
}
