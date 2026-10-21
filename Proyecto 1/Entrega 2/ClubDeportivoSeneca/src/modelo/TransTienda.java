package modelo;

public class TransTienda extends AbstractTransaccion {

	private static final long serialVersionUID = 1L;

	public static final double IVA = 0.19;

	public TransTienda(String fecha, double descuento, int puntosRedimidos) {
		super(fecha, descuento, puntosRedimidos);
	}

	@Override
	public double calcularTotal() {
		double base = calcularBase();
		return base + base * IVA;
	}
}
