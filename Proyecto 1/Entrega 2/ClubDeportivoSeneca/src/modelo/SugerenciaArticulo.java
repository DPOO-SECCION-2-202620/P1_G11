package modelo;

import java.io.Serializable;

public class SugerenciaArticulo implements Serializable {

	private static final long serialVersionUID = 1L;

	private RolEmpleado proponente;

	private String nombre;

	private String categoria;

	private double precio;

	private EstadoSolicitud estado;

	public SugerenciaArticulo(RolEmpleado proponente, String nombre, String categoria, double precio) {
		this.proponente = proponente;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.estado = EstadoSolicitud.PENDIENTE;
	}

	public EstadoSolicitud getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}

	public RolEmpleado getProponente() {
		return proponente;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public double getPrecio() {
		return precio;
	}
}
