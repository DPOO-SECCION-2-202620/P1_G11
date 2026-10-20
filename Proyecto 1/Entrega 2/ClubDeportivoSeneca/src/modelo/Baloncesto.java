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
		// TODO: valor del enunciado
		throw new UnsupportedOperationException("Pendiente");
	}

	@Override
	public int getPuntosVictoria() {
		// TODO: valor del enunciado
		throw new UnsupportedOperationException("Pendiente");
	}

	@Override
	public int getPuntosEmpate() {
		// TODO: valor del enunciado
		throw new UnsupportedOperationException("Pendiente");
	}

	@Override
	public int getPuntosDerrota() {
		// TODO: valor del enunciado
		throw new UnsupportedOperationException("Pendiente");
	}

	@Override
	public List<String> getPosicionesValidas() {
		// TODO: valor del enunciado
		throw new UnsupportedOperationException("Pendiente");
	}
}
