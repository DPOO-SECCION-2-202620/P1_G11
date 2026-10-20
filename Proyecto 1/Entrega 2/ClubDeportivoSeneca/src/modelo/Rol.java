package modelo;

import java.io.Serializable;

public abstract class Rol implements Serializable {

	private static final long serialVersionUID = 1L;

	private Persona persona;

	private boolean activo;

	protected Rol(Persona persona) {
		this.persona = persona;
		this.activo = true;
	}

	public Persona getPersona() {
		return persona;
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
}
