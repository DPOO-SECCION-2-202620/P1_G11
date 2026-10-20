package modelo;

import java.util.ArrayList;
import java.util.List;

public class Persona extends Usuario {

	private static final long serialVersionUID = 1L;

	private int puntosFidelidad;

	private int edad;

	private List<Rol> roles;

	private List<AbstractTransaccion> transacciones;

	public Persona(String login, String password, String nombre, int edad) {
		super(login, password, nombre);
		this.puntosFidelidad = 0;
		this.edad = edad;
		this.roles = new ArrayList<>();
		this.transacciones = new ArrayList<>();
	}

	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public int getEdad() {
		return edad;
	}

	public List<AbstractTransaccion> getTransacciones() {
		return transacciones;
	}

	@Override
	public boolean estaActivo() {
		for (Rol r : roles) {
			if (r.estaActivo()) {
				return true;
			}
		}
		return false;
	}

	public void agregarRol(Rol rol) {
		if (rol.getPersona() != this) {
			throw new IllegalArgumentException("El rol pertenece a otra persona");
		}
		for (Rol r : roles) {
			if (r.getClass() == rol.getClass()) {
				throw new IllegalArgumentException("El usuario ya cuenta con este rol");
			}
		}
		roles.add(rol);
	}

	public RolJugador getRolJugador() {
		for (Rol r : roles) {
			if (r instanceof RolJugador) {
				return (RolJugador) r;
			}
		}
		throw new IllegalStateException(getNombre() + " no tiene rol de jugador");
	}

	public RolEmpleado getRolEmpleado() {
		for (Rol r : roles) {
			if (r instanceof RolEmpleado) {
				return (RolEmpleado) r;
			}
		}
		throw new IllegalStateException(getNombre() + " no tiene rol de empleado");
	}

	public RolEntrenador getRolEntrenador() {
		for (Rol r : roles) {
			if (r instanceof RolEntrenador) {
				return (RolEntrenador) r;
			}
		}
		throw new IllegalStateException(getNombre() + " no tiene rol de entrenador");
	}

	public void acumularPuntos(double valorCompra) {
		if (valorCompra < 0) {
			throw new IllegalArgumentException("El valor de la compra no puede ser negativo");
		}
		int puntos = (int) Math.round(valorCompra * 0.02);
		puntosFidelidad += puntos;
	}

	public void redimirPuntos(int puntos) {
		if (puntos <= 0) {
			throw new IllegalArgumentException("Los puntos a redimir deben ser positivos");
		}
		if (puntosFidelidad < puntos) {
			throw new IllegalArgumentException("Puntos insuficientes: tiene " + puntosFidelidad);
		}
		puntosFidelidad -= puntos;
	}

	public void agregarTransaccion(AbstractTransaccion t) {
		transacciones.add(t);
	}
}
