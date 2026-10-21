package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDisciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	private Jugador jugador;

	private DepIndividual deporte;

	private Nivel nivel;

	private int ranking;

	private List<Integer> historialRanking;

	public InscripcionDisciplina(Jugador jugador, DepIndividual deporte) {
		this.jugador = jugador;
		this.deporte = deporte;
		this.nivel = Nivel.PRINCIPIANTE;
		this.ranking = 0;
		this.historialRanking = new ArrayList<>();
	}

	public Nivel getNivel() {
		return nivel;
	}

	public int getRanking() {
		return ranking;
	}

	public List<Integer> getHistorialRanking() {
		return historialRanking;
	}

	public void actualizarRanking(int nuevo) {
		if (nuevo < 0) {
			ranking = 0;
		} else {
			ranking = nuevo;
		}
		historialRanking.add(ranking);
	}

	public DepIndividual getDeporte() {
		// TODO Auto-generated method stub
		return deporte;
	}
}
