package modelo;

public class RolEntrenador extends RolEmpleado {

	private static final long serialVersionUID = 1L;

	public RolEntrenador(Persona persona, String codigoDescuento) {
		super(persona, codigoDescuento);
	}

	@Override
	public boolean esEntrenador() {
		return true;
	}
}
