package modelo;

public class PartidoCompetencia extends AbstractPartidoEquipos {

	private static final long serialVersionUID = 1L;

	private AbstractCompetencia competencia;

	private Equipo equipoClub;

	private String equipoExterno;

	private String lugar;

	private Fase fase;

	public PartidoCompetencia(AbstractCompetencia competencia, Equipo equipoClub, String equipoExterno, String lugar, String fecha, Fase fase) {
		super(fecha, competencia.getDeporte());
		this.competencia = competencia;
		this.equipoClub = equipoClub;
		this.equipoExterno = equipoExterno;
		this.lugar = lugar;
		this.fase = fase;
	}

	@Override
	public void registrarResultado(int local, int visitante) {
		// TODO: D-14: además sumar los puntos al equipo del club en su participación
		throw new UnsupportedOperationException("Pendiente");
	}
}
