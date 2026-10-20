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

	private List<Reserva> reservas;

	private List<Entrenamiento> entrenamientos;

	public Cancha(int id, boolean techada, int capacidad, AbstractDeporte deporte) {
		this.id = id;
		this.techada = techada;
		this.capacidad = capacidad;
		this.deporte = deporte;
		this.reservas = new ArrayList<>();
		this.entrenamientos = new ArrayList<>();
	}

	public AbstractDeporte getDeporte() {
		return deporte;
	}

	public boolean estaDisponible(String fecha, int inicio, int fin) {
		// TODO: D-18: revisar reservas y entrenamientos
		throw new UnsupportedOperationException("Pendiente");
	}

	public void agregarReserva(Reserva reserva) {
		reservas.add(reserva);
	}

	public void cancelarReserva(Reserva reserva) {
		// TODO: RF-SOC-05
		throw new UnsupportedOperationException("Pendiente");
	}

	public void agregarEntrenamiento(Entrenamiento entrenamiento) {
		entrenamientos.add(entrenamiento);
	}
}
