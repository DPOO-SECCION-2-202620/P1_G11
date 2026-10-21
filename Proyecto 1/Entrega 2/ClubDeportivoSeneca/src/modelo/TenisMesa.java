package modelo;

public class TenisMesa extends DepIndividual {

	private static final long serialVersionUID = 1L;

	public TenisMesa(int maxSimultaneas) {
		super("TenisMesa", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasTenisdeMesa();
	}

	@Override
	public boolean admiteJugadores(int cantidad) {
		return cantidad == 2 || cantidad == 4;
	}
}
