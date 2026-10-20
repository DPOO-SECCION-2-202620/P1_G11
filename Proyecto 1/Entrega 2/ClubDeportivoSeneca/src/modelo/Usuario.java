package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String password;

	private String nombre;

	private boolean activo;

	private int puntosFidelidad;

	private List<AbstractTransaccion> transacciones;

	protected Usuario(String login, String password, String nombre) {
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.activo = true;
		this.puntosFidelidad = 0;
		this.transacciones = new ArrayList<>();
	}

	public String getLogin() {
		return login;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public List<AbstractTransaccion> getTransacciones() {
		return transacciones;
	}

	public boolean autenticar(String password) {
		return this.password.equals(password);
	}

	public boolean estaActivo() {
		return activo;
	}

	public void activar() {
		activo = true;
	}

	public void desactivar() {
		activo = false;
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
