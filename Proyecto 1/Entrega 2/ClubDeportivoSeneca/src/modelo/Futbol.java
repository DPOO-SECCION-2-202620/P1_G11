package modelo;

import java.util.List;

public class Futbol extends DepConjunto {

	private static final long serialVersionUID = 1L;

	public Futbol(int maxSimultaneas) {
		super("Futbol", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasFutbol();
	}

	@Override
	public int getMinJugadores() {
		return 7;
	}

	@Override
	public int getPuntosVictoria() {
		return 3;
	}

	@Override
	public int getPuntosEmpate() {
		return 1;
	}

	@Override
	public int getPuntosDerrota() {
		return 0;
	}

	@Override
	public List<String> getPosicionesValidas() {
		return List.of("portero", "defensa", "mediocampista", "delantero");
	}

	@Override
	public boolean permiteEmpate() {
		return true;
	}
}
