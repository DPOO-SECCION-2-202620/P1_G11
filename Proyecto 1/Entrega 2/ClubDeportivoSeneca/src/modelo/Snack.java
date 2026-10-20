package modelo;

import java.util.List;

public class Snack extends Articulo {

	private static final long serialVersionUID = 1L;

	private List<String> alergenos;

	public Snack(String nombre, String categoria, double precio, List<String> alergenos) {
		super(nombre, categoria, precio);
		this.alergenos = alergenos;
	}

	public List<String> getAlergenos() {
		return alergenos;
	}
}
