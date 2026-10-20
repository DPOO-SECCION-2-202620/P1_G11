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
		if (!(equipo.getCategoria() == categoria)) {
			throw new IllegalStateException("La categoria del equipo no corresponde a la de la competenica");
		}
		if (!(equipo.getDeporte() == deporte)) {
			throw new IllegalStateException("El deporte del equipo no corresponde a la de la competenica");
		}
		if (nomina.size() < minimoJugadoresNomina) {
			throw new IllegalStateException("Jugadores insuficientes para la competencia");

		}
		for (ParticipacionCompe p : participaciones) {
			if (p.getEquipo() == equipo) {
				throw new IllegalStateException("El equipo ya se encuentra inscrito");
			}
		}

		ParticipacionCompe p = new ParticipacionCompe(this, equipo);
		participaciones.add(p);
		for (RolJugador rolJugador : nomina) {
			p.getNomina().add(new JugadorNomina(rolJugador));
		}
	}

	public void agregarPartido(PartidoCompetencia partido) {
		partidos.add(partido);
	}

	public ParticipacionCompe getParticipacion(Equipo equipo) {
		for (ParticipacionCompe participacion : participaciones) {
			if (participacion.getEquipo() == equipo) {
				return  participacion;
			}
		}
		throw new IllegalStateException("El equipo no se encuentra inscrito");
	}

	public RolJugador getGoleador() {
		// TODO: D-12: calcular recorriendo los partidos
		throw new UnsupportedOperationException("Pendiente");
	}
}
