package gestores;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.AbstractEmpleado;
import modelo.ClubDeportivo;
import modelo.EstadoSolicitud;
import modelo.SolicitudCambioTurno;
import modelo.TurnoEmpleado;

public class gestorTurnos {
	private ClubDeportivo club;
	public gestorTurnos(ClubDeportivo club) {
		super();
		this.club = club;
	}
	public void asignarTurno(AbstractEmpleado empleado, DayOfWeek dia, int inicio, int fin) {
		if (!empleado.estaActivo()) {
			throw new IllegalArgumentException("El empleado no esta activo");
		}if (!club.bloqueValido(inicio, fin)) {
			throw new IllegalArgumentException("El turno esta fuera del horario de atencion");
		}for (TurnoEmpleado t : club.getTurnos()) {
			if (t.getEmpleado() == empleado && t.getDia() == dia && inicio < t.getHoraFin() && t.getHoraInicio() < fin) {
				throw new IllegalStateException("El empleado ya tiene un turno que se cruza");
			}
		}List<TurnoEmpleado> turnos = club.getTurnos();
		turnos.add(new TurnoEmpleado(empleado, dia, inicio, fin));
	}
	public void retirarTurno(TurnoEmpleado turno) {
		List<TurnoEmpleado> turnos = club.getTurnos();
		if (!turnos.contains(turno)) {
			throw new IllegalArgumentException("El turno no existe");
		}turnos.remove(turno);
		if (!cumpleCobertura()) {
			turnos.add(turno);
			throw new IllegalStateException("Sin ese turno no se cumple la cobertura minima");
		}
	}
	public List<TurnoEmpleado> getTurnos(AbstractEmpleado empleado) {
		List<TurnoEmpleado> rta = new ArrayList<>();
		for (TurnoEmpleado t : club.getTurnos()) {
			if (t.getEmpleado() == empleado) {
				rta.add(t);
			}
		}return rta;
	}
	public void solicitarCambioTurno(AbstractEmpleado empleado, TurnoEmpleado turno, DayOfWeek dia, int inicio, int fin) {
		if (!club.getTurnos().contains(turno) || turno.getEmpleado() != empleado) {
			throw new IllegalArgumentException("El turno no existe o no es del empleado");
		}if (!club.bloqueValido(inicio, fin)) {
			throw new IllegalArgumentException("El turno nuevo esta fuera del horario de atencion");
		}for (SolicitudCambioTurno s : club.getSolicitudes()) {
			if (s.getTurnoActual() == turno && s.getEstado() == EstadoSolicitud.PENDIENTE) {
				throw new IllegalStateException("Ese turno ya tiene una solicitud pendiente");
			}
		}List<SolicitudCambioTurno> solicitudes = club.getSolicitudes();
		solicitudes.add(new SolicitudCambioTurno(empleado, turno, new TurnoEmpleado(empleado, dia, inicio, fin), LocalDate.now().toString()));
	}
	public void resolverSolicitud(SolicitudCambioTurno solicitud, boolean aprobada) {
		if (!club.getSolicitudes().contains(solicitud) || solicitud.getEstado() != EstadoSolicitud.PENDIENTE) {
			throw new IllegalStateException("La solicitud no existe o no esta pendiente");
		}if (!aprobada) {
			solicitud.setEstado(EstadoSolicitud.RECHAZADA);
			return;
		}List<TurnoEmpleado> turnos = club.getTurnos();
		TurnoEmpleado act = solicitud.getTurnoActual();
		TurnoEmpleado nue = solicitud.getTurnoNuevo();
		if (!turnos.contains(act)) {
			throw new IllegalStateException("El turno actual ya no existe");
		}turnos.remove(act);
		turnos.add(nue);
		if (!cumpleCobertura()) {
			turnos.remove(nue);
			turnos.add(act);
			throw new IllegalStateException("El cambio no cumple la cobertura minima");
		}solicitud.setEstado(EstadoSolicitud.APROBADA);
	}
	private boolean cumpleCobertura() {
		for (DayOfWeek d : DayOfWeek.values()) {
			for (int h = club.getHoraApertura(); h < club.getHoraCierre(); h++) {
				int nEnt = 0;
				int nFis = 0;
				for (TurnoEmpleado t : club.getTurnos()) {
					if (t.getDia() == d && t.getHoraInicio() <= h && h < t.getHoraFin()) {
						if (t.getEmpleado().esEntrenador()) {
							nEnt++;
						}else {
							nFis++;
						}
					}
				}if (nEnt < 2 || nFis < 1) {
					return false;
				}
			}
		}return true;
	}
}