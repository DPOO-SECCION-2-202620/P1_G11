package modelo;

import java.util.List;

public class Voleibol extends DepConjunto {

	private static final long serialVersionUID = 1L;

	public Voleibol(int maxSimultaneas) {
		super("Voleibol", maxSimultaneas);
	}

	@Override
	public AbstractEstadisticas crearEstadisticas() {
		return new EstadisticasVolei();
	}

	@Override
	public int getMinJugadores() {
		return 6;
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
		return List.of("armador", "opuesto", "central", "receptor", "libero");
	}
}
