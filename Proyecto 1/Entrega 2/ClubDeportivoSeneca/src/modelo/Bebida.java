package modelo;

public class Bebida extends Articulo {

	private static final long serialVersionUID = 1L;

	private boolean caliente;

	public Bebida(String nombre, String categoria, double precio, boolean caliente) {
		super(nombre, categoria, precio);
		this.caliente = caliente;
	}

	public boolean isCaliente() {
		return caliente;
	}
	
	
}
