package modelo;

import java.util.ArrayList;
import java.util.List;

public class Ascun extends AbstractCompetencia {

	private static final long serialVersionUID = 1L;

	private List<Fase> fases;

	public Ascun(String nombre, String organizador, Categoria categoria, int temporada, int minimoJugadoresNomina, DepConjunto deporte) {
		super(nombre, organizador, categoria, temporada, minimoJugadoresNomina, deporte);
		this.fases = new ArrayList<>();
	}

	public void agregarFase(Fase fase) {
		fases.add(fase);
	}
}
