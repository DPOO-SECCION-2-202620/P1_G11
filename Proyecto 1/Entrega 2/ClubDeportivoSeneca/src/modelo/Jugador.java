package modelo;

import java.util.ArrayList;
import java.util.List;

public class Jugador extends Usuario {

	private static final long serialVersionUID = 1L;

	private int edad;

	private boolean tambienEntrenador;

	private List<InscripcionDisciplina> inscripciones;

	public Jugador(String login, String password, String nombre, int edad) {
		super(login, password, nombre);
		this.edad = edad;
		this.tambienEntrenador = false;
		this.inscripciones = new ArrayList<>();
	}

	public int getEdad() {
		return edad;
	}

	public List<InscripcionDisciplina> getInscripciones() {
		return inscripciones;
	}

	public boolean esTambienEntrenador() {
		return tambienEntrenador;
	}

	public void setTambienEntrenador(boolean valor) {
		tambienEntrenador = valor;
	}

	public void agregarInscripcion(InscripcionDisciplina inscripcion) {
		inscripciones.add(inscripcion);
	}

	public void retirarInscripcion(InscripcionDisciplina inscripcion) {
		inscripciones.remove(inscripcion);
	}
}
