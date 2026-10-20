package modelo;

import java.util.ArrayList;
import java.util.List;

public class RolJugador extends Rol {

	private static final long serialVersionUID = 1L;

	private List<InfoJugador> equipos;

	private List<InscripcionDisciplina> inscripciones;

	public RolJugador(Persona persona) {
		super(persona);
		this.equipos = new ArrayList<>();
		this.inscripciones = new ArrayList<>();
	}

	public List<InscripcionDisciplina> getInscripciones() {
		return inscripciones;
	}

	public void agregarInfoEquipo(InfoJugador info) {
		equipos.add(info);
	}

	public void quitarInfoEquipo(InfoJugador info) {
		equipos.remove(info);
	}

	public List<InfoJugador> getInfoEquipos() {
		return equipos;
	}

	public void agregarInscripcion(InscripcionDisciplina inscripcion) {
		inscripciones.add(inscripcion);
	}
}
