package modelo;

public class EstadisticasFutbol extends AbstractEstadisticas {

	private static final long serialVersionUID = 1L;

	private int Goles;

	private int Asistencias;

	private int Amarillas;

	private int Rojas;

	public EstadisticasFutbol() {
		this.Goles = 0;
		this.Asistencias = 0;
		this.Amarillas = 0;
		this.Rojas = 0;
	}
	public int getAmarillas() {
		return Amarillas;
	}
	public int getRojas() {
		return Rojas;
	}
	public int getGoles() {
		return Goles;
	}
	public int getAsistencias() {
		return Asistencias;
	}
	
	
}
