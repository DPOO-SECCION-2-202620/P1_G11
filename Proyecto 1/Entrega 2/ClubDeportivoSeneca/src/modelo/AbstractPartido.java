package modelo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPartido implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fecha;

	private AbstractDeporte deporte;

	private Map<RolJugador, AbstractEstadisticas> estadisticas;

	protected AbstractPartido(String fecha, AbstractDeporte deporte) {
		this.fecha = fecha;
		this.deporte = deporte;
		this.estadisticas = new HashMap<>();
	}

	public AbstractDeporte getDeporte() {
		return deporte;
	}

	public AbstractEstadisticas registrarEstadisticas(RolJugador jugador) {
	
		AbstractEstadisticas est = deporte.crearEstadisticas(this, jugador);
		estadisticas.put(jugador, est);
		
		return est;
	}

	public String getFecha() {
		return fecha;
	}

	public Map<RolJugador, AbstractEstadisticas> getEstadisticas() {
		return estadisticas;
	}
}
