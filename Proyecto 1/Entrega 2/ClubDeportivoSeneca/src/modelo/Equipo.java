package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private Categoria categoria;

	private int maxJugadores;

	private boolean activo;

	private DepConjunto deporte;

	private List<InfoJugador> jugadores;

	public Equipo(String nombre, DepConjunto deporte, Categoria categoria, int maxJugadores) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.maxJugadores = maxJugadores;
		this.activo = true;
		this.deporte = deporte;
		this.jugadores = new ArrayList<>();
	}

	public DepConjunto getDeporte() {
		return deporte;
	}

	public List<InfoJugador> getJugadores() {
		return jugadores;
	}

	public InfoJugador inscribirJugador(RolJugador jugador, String posicion, int numeroCamisa) {
		if (!activo) {
			throw new IllegalStateException("El equipo se encuentra inactivo");
		}
		if (jugadores.size() >= maxJugadores) {
			throw new IllegalStateException("El equipo esta lleno");
		}
		for (InfoJugador info : jugadores) {
			if (info.getNumeroCamisa() == numeroCamisa) {
				throw new IllegalStateException("Este numero de camisa ya se encuentra ocupado");
			}
		}
		if (!categoria.admiteEdad(jugador.getPersona().getEdad())) {
			throw new IllegalStateException("La edad del jugador no se incluye en la categoria del equipo");
		};
		if (!deporte.getPosicionesValidas().contains(posicion)) {
			throw new IllegalStateException("La posicion del jugador no se encuentra entre las validas para el deporte");	
		};

		for (InfoJugador info : jugador.getInfoEquipos()) {
			if (info.getEquipo().getDeporte().equals(deporte) && info.getEquipo().isActivo()) {
				throw new IllegalStateException("El jugador ya pertenece a un equipo de este deporte");
			}
		}

		//Paso las comprobaciones

		InfoJugador infoJ = new InfoJugador(jugador, this, posicion, numeroCamisa);
		jugadores.add(infoJ);
		jugador.agregarInfoEquipo(infoJ);

		return infoJ;
	}

	public void retirarJugador(RolJugador jugador) {
		for (InfoJugador infoJ : jugadores) {
			if (infoJ.getJugador() == jugador) {
				jugadores.remove(infoJ);
				jugador.quitarInfoEquipo(infoJ);
				return;
			}
		}

		throw new IllegalArgumentException("El jugador no se encuentra en el equipo");
	}

	public void darDeBaja() {
		activo = false;
	}

	public boolean isActivo() {
		return activo;
	}

	public String getNombre() {
		return nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}
}
