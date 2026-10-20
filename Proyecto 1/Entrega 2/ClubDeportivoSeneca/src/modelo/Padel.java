package modelo;

public class Padel extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public Padel() {
		super("Padel");
	}

	@Override
	public AbstractEstadisticas crearEstadisticas(AbstractPartido partido, RolJugador jugador) {
		return new EstadisticasTenisPadel(partido, jugador);
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		// TODO: D-18, RN-08: solo 4
		throw new UnsupportedOperationException("Pendiente");
	}
}
