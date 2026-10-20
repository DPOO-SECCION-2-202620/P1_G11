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
		// TODO: RF-SOC-01, D-15: cupo, edad (RN-04), un equipo por deporte (RN-03), camiseta (RN-05), posición
		throw new UnsupportedOperationException("Pendiente");
	}

	public void retirarJugador(RolJugador jugador) {
		// TODO: RF-SOC-03
		throw new UnsupportedOperationException("Pendiente");
	}

	public void darDeBaja() {
		// TODO: RF-ADM-04
		throw new UnsupportedOperationException("Pendiente");
	}
}
