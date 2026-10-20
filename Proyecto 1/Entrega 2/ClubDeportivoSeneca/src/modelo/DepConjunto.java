package modelo;

import java.util.List;

public abstract class DepConjunto extends AbstractDeporte {

	private static final long serialVersionUID = 1L;

	protected DepConjunto(String nombre, int maxSimultaneas) {
		super(nombre, maxSimultaneas);
	}

	public abstract int getMinJugadores();

	public abstract int getPuntosVictoria();

	public abstract int getPuntosEmpate();

	public abstract int getPuntosDerrota();

	public abstract List<String> getPosicionesValidas();

	public boolean permiteEmpate() {
		return false;
	}
}
