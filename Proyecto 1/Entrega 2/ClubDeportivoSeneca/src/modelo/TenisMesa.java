package modelo;

public class TenisMesa extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public TenisMesa() {
		super("TenisMesa");
	}

	@Override
	public AbstractEstadisticas crearEstadisticas(AbstractPartido partido, RolJugador jugador) {
		return new EstadisticasTenisMesa(partido, jugador);
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		// TODO: D-18, RN-06/07: 2 o 4
		throw new UnsupportedOperationException("Pendiente");
	}
}
