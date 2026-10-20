package modelo;

import java.io.Serializable;

public class Ocupacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;

	private int horaInicio;

	private int horaFin;

	public Ocupacion(String fecha, int horaInicio, int horaFin) {
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public String getFecha() {
		return fecha;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public boolean seCruza(String fecha, int horaInicio, int horaFin) {
		// TODO: misma fecha y a < d && c < b
		return this.fecha.equals(fecha) && this.horaInicio < horaFin && horaInicio < this.horaFin;
	}
}
