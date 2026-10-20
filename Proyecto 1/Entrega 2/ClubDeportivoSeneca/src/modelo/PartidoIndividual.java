package modelo;

public class PartidoIndividual extends AbstractPartido {

	private static final long serialVersionUID = 1L;

	private Reserva reserva;

	public PartidoIndividual(String fecha, DepIndividual deporte, Reserva reserva) {
		super(fecha, deporte);
		this.reserva = reserva;
	}

	public void actualizarRankings() {
		// TODO: D-17: ganador por sets, +1 / -1, nunca negativo
		throw new UnsupportedOperationException("Pendiente");
	}
}
