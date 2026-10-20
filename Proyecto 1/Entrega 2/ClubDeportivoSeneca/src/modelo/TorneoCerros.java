package modelo;

public class TorneoCerros extends AbstractCompetencia {

	private static final long serialVersionUID = 1L;

	public TorneoCerros(String nombre, String organizador, Categoria categoria, int temporada, int minimoJugadoresNomina, DepConjunto deporte) {
		super(nombre, organizador, categoria, temporada, minimoJugadoresNomina, deporte);
	}
}
