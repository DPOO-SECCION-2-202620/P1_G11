package modelo;

import java.io.Serializable;

public class SolicitudCambioTurno implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolEmpleado empleado;

	private TurnoEmpleado turnoActual;

	private TurnoEmpleado turnoNuevo;

	private String fecha;

	private EstadoSolicitud estado;

	public SolicitudCambioTurno(RolEmpleado empleado, TurnoEmpleado turnoActual, TurnoEmpleado turnoNuevo, String fecha) {
		this.empleado = empleado;
		this.turnoActual = turnoActual;
		this.turnoNuevo = turnoNuevo;
		this.fecha = fecha;
		this.estado = EstadoSolicitud.PENDIENTE;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public RolEmpleado getEmpleado() {
		return empleado;
	}

	public TurnoEmpleado getTurnoActual() {
		return turnoActual;
	}

	public TurnoEmpleado getTurnoNuevo() {
		return turnoNuevo;
	}
}
