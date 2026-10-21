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

	public AbstractDeporte getDeporte() {
		return deporte;
	}

	public boolean estaDisponible(String fecha, int horaInicio, int horaFin) {
		// TODO: ninguna ocupacion seCruza
			for (Ocupacion o : ocupaciones) {
		if (o.seCruza(fecha, horaInicio, horaFin)) {
			return false;
		}
	}
	return true;
	}

	public void ocupar(String fecha, int horaInicio, int horaFin) {
		// TODO: validar disponible y agregar Ocupacion
		if (!estaDisponible(fecha, horaInicio, horaFin)) {
		throw new IllegalStateException("La cancha no esta disponible en ese horario");
	}
	ocupaciones.add(new Ocupacion(fecha, horaInicio, horaFin));
}


	public void liberar(String fecha, int horaInicio, int horaFin) {
		// TODO: quitar la Ocupacion de ese bloque
		for (int i = 0; i < ocupaciones.size(); i++) {
		Ocupacion o = ocupaciones.get(i);
		if (o.getFecha().equals(fecha) && o.getHoraInicio() == horaInicio && o.getHoraFin() == horaFin) {
			ocupaciones.remove(i);
			return;
		}
	}
}

	public boolean tieneCapacidadPara(int cantidad) {
		// TODO: cantidad <= capacidad
		return cantidad <= capacidad;
}

	public boolean permiteBebidaCaliente() {
		// TODO: RN-12: no si es padel o tenis de mesa techada
		return !(techada && (deporte instanceof Padel || deporte instanceof TenisMesa));
}
}
