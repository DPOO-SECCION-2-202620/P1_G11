package modelo;

import java.util.List;

public class Baloncesto extends DepConjunto {

	private static final long serialVersionUID = 1L;

	public Baloncesto() {
		super("Baloncesto");
	}

	@Override
	public AbstractEstadisticas crearEstadisticas(AbstractPartido partido, RolJugador jugador) {
		return new EstadisticasBasket(partido, jugador);
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
