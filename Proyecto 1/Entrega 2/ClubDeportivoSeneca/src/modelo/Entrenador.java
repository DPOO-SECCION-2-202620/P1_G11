package modelo;

public class Entrenador extends AbstractEmpleado {

	private static final long serialVersionUID = 1L;

	public Entrenador(String login, String password, String nombre, String codigoDescuento) {
		super(login, password, nombre, codigoDescuento);
	}

	@Override
	public boolean esEntrenador() {
		return true;
	}
}
