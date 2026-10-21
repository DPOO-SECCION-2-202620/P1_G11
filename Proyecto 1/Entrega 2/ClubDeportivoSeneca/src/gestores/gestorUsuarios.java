package gestores;

import java.util.ArrayList;
import java.util.List;

import modelo.ClubDeportivo;
import modelo.Equipo;
import modelo.InscripcionDisciplina;
import modelo.Jugador;
import modelo.Usuario;

public class gestorUsuarios {
	private ClubDeportivo club;
	public gestorUsuarios(ClubDeportivo club) {
		super();
		this.club = club;
	}
	public Usuario autenticar(String login, String password) {
		for (Usuario u : club.getUsuarios()) {
			if (u.getLogin().equals(login)) {
				if (!u.autenticar(password)) {
					throw new IllegalArgumentException("Clave incorrecta");
				}if (!u.estaActivo()) {
					throw new IllegalStateException("El usuario esta inactivo");
				}return u;
			}
		}throw new IllegalArgumentException("El usuario no existe");
	}
	public Jugador registrarJugador(String login, String password, String nombre, int edad) {
		if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || edad <= 0) {
			throw new IllegalArgumentException("Faltan datos del jugador");
		}for (Usuario u : club.getUsuarios()) {
			if (u.getLogin().equals(login)) {
				throw new IllegalArgumentException("El login ya está en uso");
			}
		}Jugador jug = new Jugador(login, password, nombre, edad);
		List<Usuario> usuarios = club.getUsuarios();
		usuarios.add(jug);
		return jug;
	}
	public void darDeBajaJugador(Jugador jugador) {
		if (!jugador.estaActivo()) {
			throw new IllegalStateException("El jugador ya está dado de baja");
		}jugador.desactivar();
		for (Equipo e : club.getEquipos()) {
			if (e.getInfo(jugador) != null) {
				e.retirarJugador(jugador);
			}
		}for (InscripcionDisciplina ins : new ArrayList<>(jugador.getInscripciones())) {
			jugador.retirarInscripcion(ins);
		}
	}
}