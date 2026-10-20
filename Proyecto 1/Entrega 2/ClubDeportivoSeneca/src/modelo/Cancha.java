package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cancha implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private boolean techada;

	private int capacidad;

	private AbstractDeporte deporte;

	private List<Ocupacion> ocupaciones;

	public Cancha(int id, boolean techada, int capacidad, AbstractDeporte deporte) {
		this.id = id;
		this.techada = techada;
		this.capacidad = capacidad;
		this.deporte = deporte;
		this.ocupaciones = new ArrayList<>();
	}

	public List<Ocupacion> getOcupaciones() {
		return ocupaciones;
	}

	public boolean estaDisponible(String fecha, int horaInicio, int horaFin) {
		// TODO: ninguna ocupacion seCruza
		throw new UnsupportedOperationException("Pendiente");
	}

	public void ocupar(String fecha, int horaInicio, int horaFin) {
		// TODO: validar disponible y agregar Ocupacion
		throw new UnsupportedOperationException("Pendiente");
	}

	public void liberar(String fecha, int horaInicio, int horaFin) {
		// TODO: quitar la Ocupacion de ese bloque
		throw new UnsupportedOperationException("Pendiente");
	}

	public boolean tieneCapacidadPara(int cantidad) {
		// TODO: cantidad <= capacidad
		throw new UnsupportedOperationException("Pendiente");
	}

	public boolean permiteBebidaCaliente() {
		// TODO: RN-12: no si es padel o tenis de mesa techada
		throw new UnsupportedOperationException("Pendiente");
	}
}
