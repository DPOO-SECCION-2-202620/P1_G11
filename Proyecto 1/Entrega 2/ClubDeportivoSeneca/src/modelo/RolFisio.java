package modelo;

public class RolFisio extends RolEmpleado {

	private static final long serialVersionUID = 1L;

	public RolFisio(Persona persona, String codigoDescuento) {
		super(persona, codigoDescuento);
	}

	@Override
	public boolean esEntrenador() {
		return false;
	}
}
