package modelo;

public class Padel extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public Padel(int maxSimultaneas) {
		super("Padel", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasTenisPadel();
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		// TODO: D-18, RN-08: solo 4
		return cantidad == 4;
	}
}
