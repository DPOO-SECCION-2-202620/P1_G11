package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPartidoEquipos extends AbstractPartido {

	private static final long serialVersionUID = 1L;
	private int marcadorLocal;
	private boolean resultadoRegistrado;
	private int marcadorVisitante;
	private List<Jugador> alineacion;

	protected AbstractPartidoEquipos(String fecha, DepConjunto deporte) {
		super(fecha, deporte);
		this.marcadorLocal = 0;
		this.marcadorVisitante = 0;
		this.alineacion = new ArrayList<>();
		this.resultadoRegistrado = false;
	}

	public void registrarAlineacion(Equipo equipo, List<Jugador> jugadores) {
		if (jugadores.size() < ((DepConjunto) getDeporte()).getMinJugadores()) {
			throw new IllegalArgumentException("Cantidad de jugadores insuficiente");
		}
		for (Jugador j : jugadores) {
			InfoJugador inforJ = equipo.getInfo(j);
			if (inforJ == null) {
				throw new IllegalArgumentException(j.getNombre() + " no pertenece al equipo");
			}
			if (inforJ.isSuspendido()) {
				throw new IllegalArgumentException("Hay un jugador suspendido en la alineacion");
			}
		}
		alineacion.addAll(jugadores);
	}
	public boolean isResultadoRegistrado() {
		return resultadoRegistrado;
	}
	public void registrarResultado(int local, int visitante) {
		if (resultadoRegistrado) {
			throw new IllegalStateException("El resultado de este partido ya fue registrado");
		}
		if (local < 0 || visitante < 0) {
			throw new IllegalArgumentException("El marcador no puede ser negativo");
		}
		if (local == visitante && !((DepConjunto) getDeporte()).permiteEmpate()) {
			throw new IllegalArgumentException("El deporte no permite registrar empates");
		}
		marcadorLocal = local;
		marcadorVisitante = visitante;
		resultadoRegistrado = true;
	}
}
