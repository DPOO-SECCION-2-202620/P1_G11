package modelo;

import java.io.Serializable;

public class ParticipacionFase implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fase fase;

	private Equipo equipo;

	private int puntosFase;

	private boolean avanzo;

	public ParticipacionFase(Fase fase, Equipo equipo) {
		this.fase = fase;
		this.equipo = equipo;
		this.puntosFase = 0;
		this.avanzo = false;
	}

	public boolean isAvanzo() {
		return avanzo;
	}

	public void setAvanzo(boolean avanzo) {
		this.avanzo = avanzo;
	}
}
