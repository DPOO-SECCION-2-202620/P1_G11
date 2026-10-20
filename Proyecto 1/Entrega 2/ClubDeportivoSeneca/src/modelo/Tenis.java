package modelo;

public class Tenis extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public Tenis(int maxSimultaneas) {
		super("Tenis", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasTenisPadel();
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		// TODO: D-18, RN-06/07: 2 o 4
		return cantidad == 2 || cantidad == 4;
	}
}
