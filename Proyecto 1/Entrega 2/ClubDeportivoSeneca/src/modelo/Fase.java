package modelo;

import java.io.Serializable;

public class Fase implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private String tipo;

	private ParticipacionFase participacion;

	public Fase(String nombre, String tipo, Equipo equipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.participacion = new ParticipacionFase(this, equipo);
	}

	public ParticipacionFase getParticipacion() {
		return participacion;
	}
}
