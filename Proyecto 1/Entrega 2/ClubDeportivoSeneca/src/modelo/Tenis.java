package modelo;

public class Tenis extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public Tenis() {
		super("Tenis");
	}

	@Override
	public AbstractEstadisticas crearEstadisticas(AbstractPartido partido, RolJugador jugador) {
		return new EstadisticasTenisPadel(partido, jugador);
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		// TODO: D-18, RN-06/07: 2 o 4
		throw new UnsupportedOperationException("Pendiente");
	}
}
