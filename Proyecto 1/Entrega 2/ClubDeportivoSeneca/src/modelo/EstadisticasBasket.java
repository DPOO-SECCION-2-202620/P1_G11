package modelo;

public class EstadisticasBasket extends AbstractEstadisticas {

	private static final long serialVersionUID = 1L;

	private int puntos;

	private int rebotes;

	public EstadisticasBasket(AbstractPartido partido, RolJugador jugador) {
		super(partido, jugador);
		this.puntos = 0;
		this.rebotes = 0;
	}

	public int getPuntos() {
		return puntos;
	}

	public int getRebotes() {
		return rebotes;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void setRebotes(int rebotes) {
		this.rebotes = rebotes;
	}
}
