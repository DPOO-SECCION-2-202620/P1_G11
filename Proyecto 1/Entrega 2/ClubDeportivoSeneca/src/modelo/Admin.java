package modelo;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Usuario {

	private static final long serialVersionUID = 1L;

	private List<SugerenciaArticulo> sugerencias;

	public Admin(String login, String password, String nombre) {
		super(login, password, nombre);
		this.sugerencias = new ArrayList<>();
	}

	@Override
	public boolean estaActivo() {
		return true;
	}
}
