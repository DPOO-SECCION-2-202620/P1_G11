package modelo;

public class PartidoCompetencia extends AbstractPartidoEquipos {

	private static final long serialVersionUID = 1L;

	private Equipo equipoClub;

	private String equipoExterno;

	private String lugar;

	private Fase fase;

	public PartidoCompetencia(String fecha, DepConjunto deporte, Equipo equipoClub, String equipoExterno, String lugar, Fase fase) {
		super(fecha, deporte);
		this.equipoClub = equipoClub;
		this.equipoExterno = equipoExterno;
		this.lugar = lugar;
		this.fase = fase;
	}

	@Override
	public void registrarResultado(int local, int visitante) {
		// TODO: D-14: super.registrarResultado(...); los puntos de la participacion los suma GestorDeportivo (competenciaDe)
		throw new UnsupportedOperationException("Pendiente");
	}
}
