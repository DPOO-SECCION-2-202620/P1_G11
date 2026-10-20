package modelo;

import java.util.List;

public class Baloncesto extends DepConjunto {

	private static final long serialVersionUID = 1L;

	public Baloncesto(int maxSimultaneas) {
		super("Baloncesto", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasBasket();
	}

	@Override
	public int getMinJugadores() {
		return 5;
	}

	@Override
	public int getPuntosVictoria() {
		return 2;
	}

	@Override
	public int getPuntosEmpate() {
		return 0;
	}

	@Override
	public int getPuntosDerrota() {
		return 0;
	}

	@Override
	public List<String> getPosicionesValidas() {
		return List.of("base", "escolta", "alero", "ala-pivot", "pivot");
	}
}
