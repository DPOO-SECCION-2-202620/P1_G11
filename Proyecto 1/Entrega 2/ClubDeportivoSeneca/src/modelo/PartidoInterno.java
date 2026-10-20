package modelo;

public class PartidoInterno extends AbstractPartidoEquipos {

	private static final long serialVersionUID = 1L;

	private Equipo local;

	private Equipo visitante;

	public PartidoInterno(String fecha, Equipo local, Equipo visitante) {
		super(fecha, local.getDeporte());
		this.local = local;
		this.visitante = visitante;
	}

	public Equipo getLocal() {
		return local;
	}

	public Equipo getVisitante() {
		return visitante;
	}
}
