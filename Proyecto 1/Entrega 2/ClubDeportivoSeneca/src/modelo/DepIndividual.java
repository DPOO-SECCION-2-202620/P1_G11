package modelo;

public abstract class DepIndividual extends AbstractDeporte {

	private static final long serialVersionUID = 1L;

	protected DepIndividual(String nombre) {
		super(nombre);
	}

	public abstract boolean admiteJugadores(int cantidad);
}
