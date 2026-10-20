package modelo;

public abstract class RolEmpleado extends Rol {

	private static final long serialVersionUID = 1L;

	private String codigoDescuento;

	protected RolEmpleado(Persona persona, String codigoDescuento) {
		super(persona);
		this.codigoDescuento = codigoDescuento;
	}

	public abstract boolean esEntrenador();
}
