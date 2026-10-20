package modelo;

public class TransCafeteria extends AbstractTransaccion {

	private static final long serialVersionUID = 1L;

	public static final double IMPUESTO = 0.08;

	private double propina;

	public TransCafeteria(Persona comprador, String fecha, double descuento, int puntosRedimidos, double propina) {
		super(comprador, fecha, descuento, puntosRedimidos);
		this.propina = propina;
	}

	@Override
	public double calcularTotal() {
		// TODO: D-22: subtotal con descuento + impuesto + propina
		throw new UnsupportedOperationException("Pendiente");
	}
}
