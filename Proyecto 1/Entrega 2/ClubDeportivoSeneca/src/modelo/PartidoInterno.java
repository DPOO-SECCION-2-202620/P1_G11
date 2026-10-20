package modelo;

public class PartidoInterno extends AbstractPartidoEquipos {

	private static final long serialVersionUID = 1L;

	private Equipo local;

	private Equipo visitante;

	public PartidoInterno(String fecha, DepConjunto deporte, Equipo local, Equipo visitante) {
		super(fecha, deporte);
		this.local = local;
		this.visitante = visitante;
	}
}
