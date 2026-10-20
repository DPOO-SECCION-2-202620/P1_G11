package modelo;

public class Fisio extends AbstractEmpleado {

	private static final long serialVersionUID = 1L;

	public Fisio(String login, String password, String nombre, String codigoDescuento) {
		super(login, password, nombre, codigoDescuento);
	}

	@Override
	public boolean esEntrenador() {
		return false;
	}
}
