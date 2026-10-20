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

	public DepConjunto getDeporte() {
		return deporte;
	}

	public void inscribirEquipo(Equipo equipo, List<RolJugador> nomina) {
		// TODO: RF-ADM-16, D-13, RN-24
		throw new UnsupportedOperationException("Pendiente");
	}

	public void agregarPartido(PartidoCompetencia partido) {
		partidos.add(partido);
	}

	public ParticipacionCompe getParticipacion(Equipo equipo) {
		// TODO: D-14: buscar la participación del equipo
		throw new UnsupportedOperationException("Pendiente");
	}

	public RolJugador getGoleador() {
		// TODO: D-12: calcular recorriendo los partidos
		throw new UnsupportedOperationException("Pendiente");
	}
}
