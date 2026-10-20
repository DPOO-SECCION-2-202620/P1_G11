package modelo;

import java.io.Serializable;
import java.time.DayOfWeek;

public class TurnoEmpleado implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolEmpleado empleado;

	private DayOfWeek dia;

	private int horaInicio;

	private int horaFin;

	public TurnoEmpleado(RolEmpleado empleado, DayOfWeek dia, int horaInicio, int horaFin) {
		this.empleado = empleado;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
}
