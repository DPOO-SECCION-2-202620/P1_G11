package modelo;

public class EstadisticasVolei extends AbstractEstadisticas {

	private static final long serialVersionUID = 1L;

	private int aces;

	private int bloqueos;

	public EstadisticasVolei(AbstractPartido partido, RolJugador jugador) {
		super(partido, jugador);
		this.aces = 0;
		this.bloqueos = 0;
	}

	public int getAces() {
		return aces;
	}

	public int getBloqueos() {
		return bloqueos;
	}

	public void setAces(int aces) {
		this.aces = aces;
	}

	public void setBloqueos(int bloqueos) {
		this.bloqueos = bloqueos;
	}
}
