package modelo;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;

	private String password;

	private String nombre;

	protected Usuario(String login, String password, String nombre) {
		this.login = login;
		this.password = password;
		this.nombre = nombre;
	}

	public String getLogin() {
		return login;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean autenticar(String password) {
		// TODO: RF-GEN-01, D-05
		throw new UnsupportedOperationException("Pendiente");
	}

	public abstract boolean estaActivo();
}
