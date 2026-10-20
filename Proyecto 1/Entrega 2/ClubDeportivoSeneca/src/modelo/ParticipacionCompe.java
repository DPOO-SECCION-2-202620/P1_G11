package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParticipacionCompe implements Serializable {

	private static final long serialVersionUID = 1L;

	private AbstractCompetencia competencia;

	private Equipo equipo;

	private int puntosAcumulados;

	private List<JugadorNomina> nomina;

	public ParticipacionCompe(AbstractCompetencia competencia, Equipo equipo) {
		this.competencia = competencia;
		this.equipo = equipo;
		this.puntosAcumulados = 0;
		this.nomina = new ArrayList<>();
	}

	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}

	public List<JugadorNomina> getNomina() {
		return nomina;
	}

	public void sumarPuntos(int puntos) {
		puntosAcumulados += puntos;
	}

	public Equipo getEquipo() {
		return equipo;
	}
}
