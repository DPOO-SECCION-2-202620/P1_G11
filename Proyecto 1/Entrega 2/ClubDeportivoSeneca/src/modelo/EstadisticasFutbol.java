package modelo;

public class EstadisticasFutbol extends AbstractEstadisticas {

	private static final long serialVersionUID = 1L;

	private int goles;

	private int asistencias;

	private int amarillas;

	private int rojas;

	public EstadisticasFutbol(AbstractPartido partido, RolJugador jugador) {
		super(partido, jugador);
		this.goles = 0;
		this.asistencias = 0;
		this.amarillas = 0;
		this.rojas = 0;
	}

	public int getGoles() {
		return goles;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public int getAmarillas() {
		return amarillas;
	}

	public int getRojas() {
		return rojas;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	public void setAmarillas(int amarillas) {
		this.amarillas = amarillas;
	}

	public void setRojas(int rojas) {
		this.rojas = rojas;
	}
}
