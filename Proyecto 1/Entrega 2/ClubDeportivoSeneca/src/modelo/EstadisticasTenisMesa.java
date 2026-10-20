package modelo;

public class EstadisticasTenisMesa extends EstadisticasIndividual {

	private static final long serialVersionUID = 1L;

	private int setsGanados;

	private int puntos;

	public EstadisticasTenisMesa(AbstractPartido partido, RolJugador jugador) {
		super(partido, jugador);
		this.setsGanados = 0;
		this.puntos = 0;
	}

	public int getSetsGanados() {
		return setsGanados;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setSetsGanados(int setsGanados) {
		this.setsGanados = setsGanados;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
}
