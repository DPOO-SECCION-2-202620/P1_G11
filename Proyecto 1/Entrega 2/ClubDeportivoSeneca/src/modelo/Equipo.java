package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private Categoria categoria;

	private int maxJugadores;

	private boolean activo;

	private DepConjunto deporte;

	private Map<Jugador, InfoJugador> jugadores;

	public Equipo(String nombre, DepConjunto deporte, Categoria categoria, int maxJugadores) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.maxJugadores = maxJugadores;
		this.activo = true;
		this.deporte = deporte;
		this.jugadores = new HashMap<>();
	}

	public InfoJugador inscribirJugador(Jugador jugador, String posicion, int numeroCamisa) {
		if (!activo) {
			throw new IllegalStateException("El equipo se encuentra inactivo");
		}if (jugadores.size() >= maxJugadores) {
			throw new IllegalStateException("El equipo esta lleno");
		}if (jugadores.containsKey(jugador)) {
			throw new IllegalArgumentException("El jugador ya esta en el equipo");
		}if (!categoria.admiteEdad(jugador.getEdad())) {
			throw new IllegalArgumentException("La edad del jugador no se incluye en la categoria del equipo");
		}if (!deporte.getPosicionesValidas().contains(posicion)) {
			throw new IllegalArgumentException("La posicion del jugador no se encuentra entre las validas para el deporte");
		}
		InfoJugador infoJ = new InfoJugador(posicion, numeroCamisa);
		jugadores.put(jugador, infoJ);
		return infoJ;
	}

	public void retirarJugador(Jugador jugador) {
		if (jugadores.remove(jugador) == null) {
			throw new IllegalArgumentException("El jugador no se encuentra en el equipo");
		}
	}

	public void darDeBaja() {
		activo = false;
	}

	public InfoJugador getInfo(Jugador jugador) {
		return jugadores.get(jugador);
	}

	public List<Jugador> getJugadores() {
		return new ArrayList<>(jugadores.keySet());
	}

	public List<InfoJugador> getInfoJugadores() {
		return new ArrayList<>(jugadores.values());
	}

	public boolean isActivo() {
		// TODO Auto-generated method stub
		return activo;
	}

	public DepConjunto getDeporte() {
		// TODO Auto-generated method stub
		return deporte;
	}
	public Categoria getCategoria() {
		return categoria;
	}
}
