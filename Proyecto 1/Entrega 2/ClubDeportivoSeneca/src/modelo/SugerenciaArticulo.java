package modelo;

import java.io.Serializable;
import java.util.List;

public class SugerenciaArticulo implements Serializable {

	private static final long serialVersionUID = 1L;

	private AbstractEmpleado proponente;

	private String nombre;

	private String categoria;

	private double precio;

	private List<String> alergenos;

	private EstadoSolicitud estado;

	public SugerenciaArticulo(AbstractEmpleado proponente, String nombre, String categoria, double precio, List<String> alergenos) {
		this.proponente = proponente;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.alergenos = alergenos;
		this.estado = EstadoSolicitud.PENDIENTE;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}
}
