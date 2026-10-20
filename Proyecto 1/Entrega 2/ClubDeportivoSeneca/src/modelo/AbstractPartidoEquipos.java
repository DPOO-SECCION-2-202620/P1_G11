package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPartidoEquipos extends AbstractPartido {

	private static final long serialVersionUID = 1L;

	private int marcadorLocal;

	private int marcadorVisitante;

	private List<RolJugador> alineacion;

	protected AbstractPartidoEquipos(String fecha, DepConjunto deporte) {
		super(fecha, deporte);
		this.marcadorLocal = 0;
		this.marcadorVisitante = 0;
		this.alineacion = new ArrayList<>();
	}

	public void registrarAlineacion(Equipo equipo, List<RolJugador> jugadores) {
		if (jugadores.size() < ((DepConjunto) getDeporte()).getMinJugadores()) {
			throw new IllegalArgumentException("Cantidad de jugadores insuficiente");
		}
		for (InfoJugador inforJ : equipo.getJugadores()) {
			if (jugadores.contains(inforJ.getJugador()) && inforJ.isSuspendido()) {
				throw new IllegalArgumentException("Hay un jugador suspendido en la alineacion");
			}
		}
		alineacion.addAll(jugadores);
	}

	public void registrarResultado(int local, int visitante) {
		if (local < 0 || visitante < 0) {
			throw new IllegalArgumentException("El marcador no puede ser negativo");
		}
		if (local == visitante && !((DepConjunto) getDeporte()).permiteEmpate()) {
			throw new IllegalArgumentException("El deporte no permite registrar empates");
		}
		marcadorLocal = local;
		marcadorVisitante = visitante;
	}

	public int getMarcadorLocal() {
		return marcadorLocal;
	}

	public int getMarcadorVisitante() {
		return marcadorVisitante;
	}

	public List<RolJugador> getAlineacion() {
		return alineacion;
	}
}
