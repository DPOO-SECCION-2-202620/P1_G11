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
		// TODO: RF-ENT-05, D-09: mínimo de jugadores y nadie suspendido
		throw new UnsupportedOperationException("Pendiente");
	}

	public void registrarResultado(int local, int visitante) {
		// TODO: RF-ENT-06, D-07: rechazar empate si el deporte no lo permite
		throw new UnsupportedOperationException("Pendiente");
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
