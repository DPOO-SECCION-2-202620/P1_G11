package modelo;

public abstract class AbstractEmpleado extends Usuario {

	private static final long serialVersionUID = 1L;

	private String codigoDescuento;

	protected AbstractEmpleado(String login, String password, String nombre, String codigoDescuento) {
		super(login, password, nombre);
		this.codigoDescuento = codigoDescuento;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public abstract boolean esEntrenador();
}
